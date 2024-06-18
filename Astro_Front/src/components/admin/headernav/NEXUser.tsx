import React, { useState, useEffect } from 'react';

interface AvatarProps {
  imgAvatar: string;
  onLogout: () => void;
}

const Avatar: React.FC<AvatarProps> = ({ imgAvatar, onLogout }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    // Verificar si hay un token almacenado al cargar el componente
    const token = localStorage.getItem('token');
    if (token) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, []);

  const handleLogout = () => {
    // Limpiar token en localStorage y manejar el cierre de sesión
    localStorage.removeItem('token');
    setIsLoggedIn(false);
    // Llamar a la función de cierre de sesión proporcionada por el padre
    onLogout();
  };

  return (
    <button
      id="dropdownUserAvatarButton"
      data-dropdown-toggle="dropdownAvatar"
      className="flex rounded-full bg-blue-800 text-sm focus:ring-4 focus:ring-blue-300 md:me-0 dark:focus:ring-blue-600"
      type="button"
    >
      <span className="sr-only">Abrir menú de usuario</span>
      {isLoggedIn ? (
        <>
          <a href="/" className="inline-block transform transition-transform hover:scale-110">
          
          </a>
          <button onClick={handleLogout} className="ml-2 text-white hover:underline">
            Cerrar sesión
          </button>
        </>
      ) : (
        <span>Iniciar sesión</span> // Aquí puedes cambiar el texto o agregar un enlace al formulario de inicio de sesión
      )}
    </button>
  );
};

export default Avatar;
