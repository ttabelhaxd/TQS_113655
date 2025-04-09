export default function Navbar() {
  return (
    <nav className="w-full h-20 bg-gradient-to-r from-indigo-800 to-blue-800 fixed top-0 left-0 z-50 flex items-center justify-between px-6 shadow-xl">
      <div className="flex items-center space-x-8">
        <a href="/" className="text-white text-3xl font-bold font-mono hover:text-indigo-200 transition-colors">
          🍽️ Moliceiro
        </a>
        <a
          href="/checkin"
          className="hidden md:flex items-center space-x-2 text-white/90 hover:text-white px-4 py-2 rounded-lg bg-white/10 backdrop-blur-sm transition-all"
        >
          <span>Check-in</span>
        </a>
      </div>
      
      <div className="flex items-center space-x-4">
        <a 
          href="/restaurants" 
          className="text-white/90 hover:text-white px-4 py-2 rounded-lg hover:bg-white/10 transition-colors"
        >
          Cantinas
        </a>
        <a
          href="/reservations"
          className="text-white/90 hover:text-white px-4 py-2 rounded-lg hover:bg-white/10 transition-colors"
        >
          Minhas Reservas
        </a>
      </div>
    </nav>
  );
}