import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { Carousel } from './components/Carousel'
import { RestaurantsPage } from './pages/RestaurantsPage'
import { ReservationsPage } from './pages/ReservationPage'
import { CheckinPage } from './pages/Checkin'
import Navbar from './components/Navbar'

export default function App() {
  return (
    <Router>
      <Navbar/>

      <div className="mt-20 text-center justify-center items-center">
        <Routes>
          <Route path="/" element={<Carousel />} />
          <Route path="/restaurants" element={<RestaurantsPage />} />
          <Route path="/reservations" element={<ReservationsPage />} />
          <Route path="/checkin" element={<CheckinPage />} />
        </Routes>
      </div>

      <footer className="fixed bottom-0 w-full text-center bg-gray-100 border-t border-gray-300 p-4 flex justify-center items-center">
        <div className="font-bold">TQS_113655 - HW1</div>
      </footer>
    </Router>
  )
}