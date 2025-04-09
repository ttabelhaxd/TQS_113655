import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faXmark,
  faMagnifyingGlass,
} from "@fortawesome/free-solid-svg-icons";
import { Restaurant, Meal, GroupedMeal, WeatherForecast } from "@/types/types";
import { API_BASE_URL } from "@/config/apiConfig";

export function RestaurantsPage() {
  const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
  const [selectedId, setSelectedId] = useState<number | null>(null);
  const [groupedMeals, setGroupedMeals] = useState<GroupedMeal[]>([]);
  const [selectedForecast, setSelectedForecast] = useState<WeatherForecast | null>(null);
  const [showReservationOptions, setShowReservationOptions] = useState(false);
  const [selectedDate, setSelectedDate] = useState<string | null>(null);
  const [showSuccessModal, setShowSuccessModal] = useState(false);
  const [reservationToken, setReservationToken] = useState<string | null>(null);
  const [, setErrorMessage] = useState("");
  const [, setShowErrorModal] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`${API_BASE_URL}/restaurants`)
      .then((res) => res.json())
      .then(setRestaurants)
      .catch(console.error);
  }, []);

  useEffect(() => {
    if (selectedId !== null) {
      fetch(`${API_BASE_URL}/meals?restaurantId=${selectedId}`)
        .then((res) => res.json())
        .then((data: Meal[]) => {
          const grouped: Record<string, GroupedMeal> = {};
          data.forEach((meal) => {
            if (!grouped[meal.date]) {
              grouped[meal.date] = { date: meal.date };
            }
            if (meal.type === "ALMOCO") grouped[meal.date].almoco = meal;
            if (meal.type === "JANTAR") grouped[meal.date].jantar = meal;
            grouped[meal.date].forecast = meal.forecast;
          });
          setGroupedMeals(Object.values(grouped));
        });
    }
  }, [selectedId]);

  const handleReservation = (type: "ALMOCO" | "JANTAR") => {
    if (!selectedId || !selectedDate) return;

    const stored = localStorage.getItem("reservationTokens");
    const tokens: string[] = stored ? JSON.parse(stored) : [];

    Promise.allSettled(
      tokens.map((token) =>
        fetch(`${API_BASE_URL}/reservations/${token}`).then((res) => {
          if (!res.ok) throw new Error("Token inválido");
          return res.json();
        })
      )
    ).then((results) => {
      const reservas = results
        .filter((r) => r.status === "fulfilled")
        .map((r) => (r as PromiseFulfilledResult<any>).value);

      const conflito = reservas.find(
        (r) =>
          r.date === selectedDate &&
          r.restaurantName &&
          r.type === type &&
          !r.cancelled
      );

      if (conflito) {
        setErrorMessage(
          `Já existe uma reserva para ${
            type === "ALMOCO" ? "almoço" : "jantar"
          } nesta data.`
        );
        setShowErrorModal(true);
        setShowReservationOptions(false);
        return;
      }

      fetch(`${API_BASE_URL}/reservations`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          restaurantId: selectedId,
          date: selectedDate,
          type,
        }),
      })
        .then((res) => res.json())
        .then((data) => {
          const updatedTokens = [...tokens, data.token];
          localStorage.setItem(
            "reservationTokens",
            JSON.stringify(updatedTokens)
          );
          setReservationToken(data.token);
          setShowSuccessModal(true);
          setShowReservationOptions(false);
        })
        .catch((err) => {
          console.error("Erro ao criar reserva:", err);
          setErrorMessage("Ocorreu um erro ao criar a reserva.");
          setShowErrorModal(true);
        });
    });
  };

  return (
    <div className="absolute left-1/2 -translate-x-1/2 max-w-6xl pt-28 pb-20">
      <Dialog open={showSuccessModal} onOpenChange={setShowSuccessModal}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle className="text-2xl">Reserva criada com sucesso! 🎉</DialogTitle>
          </DialogHeader>
          <div className="space-y-4">
            <p className="text-gray-600">Seu token de acesso:</p>
            <p className="font-mono p-4 bg-gray-100 rounded-xl break-all text-indigo-600">
              {reservationToken}
            </p>
            <Button 
              onClick={() => setShowSuccessModal(false)}
              className="w-full py-4 bg-indigo-600 hover:bg-indigo-700 rounded-xl"
            >
              Fechar
            </Button>
          </div>
        </DialogContent>
      </Dialog>

      <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-12 gap-4">
        <h2 className="text-4xl font-bold bg-gradient-to-r from-indigo-600 to-blue-600 bg-clip-text text-transparent">
          Cantinas Disponíveis
        </h2>
        <Button 
          onClick={() => navigate("/reservations")}
          className="bg-indigo-600 hover:bg-indigo-700 px-8 py-4 rounded-xl text-white"
        >
          📋 Minhas Reservas
        </Button>
      </div>

      <div className="relative mb-12">
        <select
          className="w-full px-6 py-4 text-lg border-2 border-gray-200 rounded-2xl appearance-none bg-white shadow-sm hover:border-indigo-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 transition-all"
          onChange={(e) => setSelectedId(Number(e.target.value))}
        >
          <option value="">-- Escolha um restaurante --</option>
          {restaurants.map((r) => (
            <option key={r.id} value={r.id}>
              {r.name}
            </option>
          ))}
        </select>
        <div className="absolute right-4 top-1/2 -translate-y-1/2 text-2xl text-gray-400">▼</div>
      </div>

      {groupedMeals.map((group, i) => (
        <div key={i} className="mb-6 bg-white rounded-2xl shadow-lg overflow-hidden">
          <div className="p-6 bg-indigo-50 border-b border-indigo-100">
            <h3 className="text-2xl font-semibold text-indigo-700">{group.date}</h3>
          </div>
          
          <div className="grid md:grid-cols-3 gap-6 p-6">
            <div className="p-4 bg-white rounded-xl border border-gray-100 hover:shadow-md transition-shadow">
              <h4 className="text-lg font-bold mb-2 text-gray-700">Almoço</h4>
              <p className="text-gray-600 mb-4">{group.almoco?.description || "-"}</p>
            </div>
            
            <div className="p-4 bg-white rounded-xl border border-gray-100 hover:shadow-md transition-shadow">
              <h4 className="text-lg font-bold mb-2 text-gray-700">Jantar</h4>
              <p className="text-gray-600 mb-4">{group.jantar?.description || "-"}</p>
            </div>
            
            <div className="p-4 bg-white rounded-xl border border-gray-100 hover:shadow-md transition-shadow">
              <h4 className="text-lg font-bold mb-2 text-gray-700">Clima</h4>
              <div className="space-y-2">
                <p className="text-gray-600">🌡️ Máx: {group.forecast?.maxTemp}°C</p>
                <p className="text-gray-600">❄️ Mín: {group.forecast?.minTemp}°C</p>
                <Button 
                  variant="outline"
                  className="w-full border-indigo-200 text-indigo-600 hover:bg-indigo-50"
                  onClick={() => setSelectedForecast(group.forecast || null)}
                >
                <FontAwesomeIcon icon={faMagnifyingGlass} className="mr-2" />
                Detalhes
              </Button>
              </div>
            </div>
          </div>

          <div className="p-6 border-t border-gray-100">
            <Button
              className="w-full py-4 bg-indigo-600 hover:bg-indigo-700 rounded-xl"
              onClick={() => {
                setSelectedDate(group.date);
                setShowReservationOptions(true);
              }}
            >
              Reservar Refeição
            </Button>
          </div>
        </div>
      ))}

      <Dialog open={!!selectedForecast} onOpenChange={() => setSelectedForecast(null)}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle className="text-2xl">Previsão para {selectedForecast?.date}</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 text-lg">
            <div className="flex items-center gap-2">
              <span>🌡️</span>
              <span>Máxima: {selectedForecast?.maxTemp}°C</span>
            </div>
            <div className="flex items-center gap-2">
              <span>❄️</span>
              <span>Mínima: {selectedForecast?.minTemp}°C</span>
            </div>
            <div className="flex items-center gap-2">
              <span>🌧️</span>
              <span>Precipitação: {selectedForecast?.precipitation} mm</span>
            </div>
          </div>
        </DialogContent>
      </Dialog>

      <Dialog open={showReservationOptions} onOpenChange={setShowReservationOptions}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle className="text-2xl">Reserva para {selectedDate}</DialogTitle>
          </DialogHeader>
          <div className="flex flex-col gap-4">
            <Button 
              onClick={() => handleReservation("ALMOCO")}
              className="py-6 text-lg bg-indigo-600 hover:bg-indigo-700 rounded-xl"
            >
              Reservar Almoço ☀️
            </Button>
            <Button 
              onClick={() => handleReservation("JANTAR")}
              className="py-6 text-lg bg-indigo-600 hover:bg-indigo-700 rounded-xl"
            >
              Reservar Jantar 🌙
            </Button>
            <Button
              variant="destructive"
              className="py-6 text-lg"
              onClick={() => setShowReservationOptions(false)}
            >
              <FontAwesomeIcon icon={faXmark} className="mr-2" />
              Cancelar
            </Button>
          </div>
        </DialogContent>
      </Dialog>
    </div>
  );
}
