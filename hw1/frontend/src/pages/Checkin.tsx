import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Card } from "@/components/ui/card"
import { API_BASE_URL } from "@/config/apiConfig"

export function CheckinPage() {
  const [token, setToken] = useState("")
  const [feedback, setFeedback] = useState<{ type: "success" | "error", message: string } | null>(null)
  const navigate = useNavigate()

  const handleCheckIn = () => {
    if (!token.trim()) return

    fetch(`${API_BASE_URL}/reservations/checkin/${token}`, {
      method: "POST"
    })
      .then(res => {
        if (res.ok) {
          setFeedback({ type: "success", message: "Entrada registada com sucesso!" })
        } else {
          return res.text().then(msg => {
            throw new Error(msg || "Erro ao fazer check-in.")
          })
        }
      })
      .catch(err => {
        setFeedback({ type: "error", message: err.message || "Token inválido ou já usado." })
      })
  }

  return (
    <div className="absolute left-1/2 -translate-x-1/2 pt-32 pb-20">
      <div className="bg-white rounded-xl shadow-xl p-8">
        <h2 className="text-3xl font-bold text-center mb-8 bg-gradient-to-r from-indigo-600 to-blue-600 bg-clip-text text-transparent">
          Check-in Digital
        </h2>
        
        <div className="space-y-6">
          <Input
            placeholder="Cole seu token aqui..."
            value={token}
            onChange={(e) => setToken(e.target.value)}
            className="py-6 px-4 text-lg border-2 border-gray-200 rounded-xl hover:border-indigo-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200"
          />
          
          <Button 
            onClick={handleCheckIn}
            className="w-full py-6 text-lg bg-indigo-600 hover:bg-indigo-700 rounded-xl transition-transform hover:scale-[1.02]"
          >
            Confirmar Entrada 🚀
          </Button>
        </div>

        {feedback && (
          <Card className={`mt-6 p-6 rounded-xl ${
            feedback.type === "success" 
              ? "bg-green-50 border-2 border-green-200"
              : "bg-red-50 border-2 border-red-200"
          }`}>
            <p className={`text-lg ${feedback.type === "success" ? "text-green-700" : "text-red-700"}`}>
              {feedback.message}
            </p>
            <Button
              variant="outline"
              className="mt-4 w-full border-indigo-200 text-indigo-600 hover:bg-indigo-50"
              onClick={() => {
                setFeedback(null)
                setToken("")
                navigate("/restaurants")
              }}
            >
              Fechar
            </Button>
          </Card>
        )}
      </div>
    </div>
  )
}