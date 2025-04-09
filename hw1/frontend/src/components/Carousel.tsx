import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { Button } from "./ui/button"
import slide1 from "../assets/slide1.jpg"
import slide2 from "../assets/slide2.jpg"
import slide3 from "../assets/slide3.jpg"
import slide4 from "../assets/slide4.jpg"
import slide5 from "../assets/slide5.jpg"

const images = [slide1, slide2, slide3, slide4, slide5]

export function Carousel() {
  const [current, setCurrent] = useState(0)
  const total = images.length
  const navigate = useNavigate()

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrent(prev => (prev === total - 1 ? 0 : prev + 1))
    }, 5000)

    return () => clearInterval(interval)
  }, [total])

  return (
    <div className="w-screen h-[calc(100vh-80px)] mt-20 relative overflow-hidden group">
      <div className="absolute inset-0 bg-gradient-to-t from-black/60 to-transparent z-10"/>
      
      <div className="absolute bottom-16 left-1/2 -translate-x-1/2 z-20 space-y-4 text-center">
        <h1 className="text-5xl font-bold text-white drop-shadow-2xl mb-4">
          Bem-vindo à Universidade Moliceiro
        </h1>
        <Button 
          size="lg" 
          className="bg-white/10 backdrop-blur-sm hover:bg-white/20 border-2 border-white/30 text-white text-xl px-12 py-6 rounded-2xl transition-all hover:scale-105"
          onClick={() => navigate("/restaurants")}
        >
          Explorar Restaurantes ➔
        </Button>
      </div>

      <div className="absolute bottom-8 left-1/2 -translate-x-1/2 flex gap-2 z-20">
        {images.map((_, i) => (
          <div 
            key={i}
            className={`h-3 w-3 rounded-full transition-colors ${i === current ? 'bg-white' : 'bg-white/30'}`}
          />
        ))}
      </div>

      <div 
        className="flex w-[500%] h-full transition-transform duration-400 ease-in-out"
        style={{ transform: `translateX(-${current * 100}vw)` }}
      >
        {images.map((src, index) => (
          <div key={index} className="w-screen h-full flex-shrink-0 flex">
            <img
              src={src}
              alt={`Slide ${index + 1}`}
              className="w-full h-full object-cover"
            />
          </div>
        ))}
      </div>
    </div>
  )
}