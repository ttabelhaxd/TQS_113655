import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { Button } from "@/components/ui/button"
import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog"
import { Reservation } from "@/types/types"
import { API_BASE_URL } from "@/config/apiConfig"

export function ReservationsPage() {
  const [reservations, setReservations] = useState<Reservation[]>([])
  const [showCancelModal, setShowCancelModal] = useState(false)
  const [cancelMessage, setCancelMessage] = useState("")
  const [selectedToken, setSelectedToken] = useState<string | null>(null)
  const navigate = useNavigate()

  useEffect(() => {
    const loadReservations = () => {
      const stored = localStorage.getItem("reservationTokens")
      if (!stored) return

      const tokens: string[] = JSON.parse(stored)
      
      Promise.allSettled(
        tokens.map(token =>
          fetch(`${API_BASE_URL}/reservations/${token}`)
            .then(res => {
              if (!res.ok) throw new Error("Token inválido")
              return res.json()
            })
        )
      ).then(results => {
        const validReservations: Reservation[] = []
        const validTokens: string[] = []

        results.forEach((r, i) => {
          if (r.status === "fulfilled") {
            validReservations.push(r.value)
            validTokens.push(tokens[i])
          }
        })

        setReservations(validReservations)
        localStorage.setItem("reservationTokens", JSON.stringify(validTokens))
      })
    }

    loadReservations()
  }, [])

  const handleCancel = (token: string) => {
    fetch(`${API_BASE_URL}/reservations/${token}`, {
      method: "DELETE"
    })
      .then(() => {
        setReservations(prev => prev.filter(r => r.token !== token))
        setCancelMessage("Reserva cancelada com sucesso.")
        setShowCancelModal(true)
        
        const stored = localStorage.getItem("reservationTokens")
        const tokens: string[] = stored ? JSON.parse(stored) : []
        const updated = tokens.filter(t => t !== token)
        localStorage.setItem("reservationTokens", JSON.stringify(updated))
      })
      .catch(err => {
        console.error("Erro ao cancelar:", err)
        setCancelMessage("Erro ao cancelar reserva.")
        setShowCancelModal(true)
      })
  }

  return (
    <div className="absolute left-1/2 -translate-x-1/2  max-w-6xl pt-32 pb-20">
      <Dialog open={showCancelModal} onOpenChange={setShowCancelModal}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle className="text-2xl">{cancelMessage}</DialogTitle>
          </DialogHeader>
          <Button 
            onClick={() => setShowCancelModal(false)}
            className="w-full py-4 bg-indigo-600 hover:bg-indigo-700 rounded-xl"
          >
            Fechar
          </Button>
        </DialogContent>
      </Dialog>

      <div className="flex flex-col md:flex-row justify-between items-start mb-12 gap-4">
        <h2 className="text-4xl font-bold bg-gradient-to-r from-indigo-600 to-blue-600 bg-clip-text text-transparent">
          Minhas Reservas
        </h2>
        <Button
          onClick={() => navigate("/restaurants")}
          className="bg-gray-100 hover:bg-gray-200 text-gray-700 px-8 py-4 rounded-xl border border-gray-200"
        >
          ← Voltar para Cantinas
        </Button>
      </div>

      {reservations.length === 0 ? (
        <div className="text-center py-20">
          <div className="text-6xl mb-4">🍽️</div>
          <p className="text-gray-500 text-xl">Nenhuma reserva ativa</p>
        </div>
      ) : (
        <div className="grid gap-4">
          {reservations.map((r, i) => (
            <div key={i} className="bg-white rounded-xl p-6 shadow-sm hover:shadow-md transition-shadow border border-gray-100">
              <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-4">
                <div className="space-y-1 flex-1">
                  <h3 className="text-xl font-semibold text-indigo-600">{r.restaurantName}</h3>
                  <p className="text-gray-600">{r.date} • {r.type === "ALMOCO" ? "Almoço" : "Jantar"}</p>
                  <span className={`inline-block px-3 py-1 rounded-full text-sm ${
                    r.cancelled ? 'bg-red-100 text-red-700' : 
                    r.checkedIn ? 'bg-green-100 text-green-700' : 'bg-blue-100 text-blue-700'
                  }`}>
                    {r.cancelled ? "Cancelada" : r.checkedIn ? "Utilizada" : "Ativa"}
                  </span>
                </div>
                
                <div className="flex gap-2">
                  <Button 
                    variant="outline"
                    className="border-indigo-200 text-indigo-600"
                    onClick={() => setSelectedToken(r.token)}
                  >
                    Ver Token
                  </Button>
                  {!r.cancelled && !r.checkedIn && (
                    <Button 
                      variant="destructive"
                      onClick={() => handleCancel(r.token)}
                    >
                      Cancelar
                    </Button>
                  )}
                </div>
              </div>
            </div>
          ))}
        </div>
      )}

      <Dialog open={!!selectedToken} onOpenChange={() => setSelectedToken(null)}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle className="text-2xl">Token da Reserva</DialogTitle>
          </DialogHeader>
          <p className="font-mono break-all p-4 bg-gray-100 rounded-xl text-indigo-600">
            {selectedToken}
          </p>
        </DialogContent>
      </Dialog>
    </div>
  )
}