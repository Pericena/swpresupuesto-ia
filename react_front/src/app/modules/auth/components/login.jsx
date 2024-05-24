import { React, useState } from 'react';
import { Helmet } from 'react-helmet';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faLock } from '@fortawesome/free-solid-svg-icons';
import { ApiLogin } from '../services/ApiLogin'; 
import { useNavigate } from 'react-router-dom';
import '@fortawesome/fontawesome-svg-core/styles.css';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await ApiLogin(username, password);
      const rol = localStorage.getItem('Rol');
      if (rol === 'DOC') {
        navigate('/admin/calificaciones');
      } else if (rol=== 'SECT') {
        navigate('/inicio');
      } else if(rol=== 'ADM'){
        navigate('/inicio');
      }else if(rol === 'EST'){
        navigate('/control');
      }else {
        setError('Rol desconocido'); 
      }

      setUsername('');
      setPassword('');
      setError(null);
    } catch (error) {
      if (error.response && error.response.data && error.response.data.errors && error.response.data.errors.message === "No autorizado") {
        navigate('/changepassword');
      } else {
        setError('Error al iniciar sesión. Verifica tus credenciales.');
      }
    }
  };

  return (
    <div className="flex flex-col h-screen md:flex-row">
  <Helmet>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
    />
  </Helmet>

  <div className="img md:w-1/2 flex justify-center items-center bg-white">
    <img src="/img/LOGIN SVG/ICON PERSONAS Y LOGO.svg" alt="Login Icon" />
  </div>
  <div
    className="login-content md:w-1/2 flex flex-col justify-center items-center text-center"
    style={{ backgroundColor: '#ffc201' }}
  >
    <form onSubmit={handleSubmit} className="w-full md:w-96 p-8">
      <h2 className="title font-montserrat text-4xl text-white uppercase mb-6">
        BIENVENIDOS
      </h2>
      <img src="/img/LOGIN SVG/ICONO 4.svg" alt="Logo" className="w-48 mx-auto mb-6" />
      <div className="flex items-center mb-2">
        <div className="w-10 h-10 bg-blue-600 flex items-center justify-center rounded-full">
          <i className="fas fa-user text-white"></i>
        </div>
        <div className="ml-2 flex-grow">
          <input
            type="text"
            placeholder="Usuario"
            id="username"
            name="username"
            className="input-login w-full"
            value={username}
            onChange={handleUsernameChange}
          />
        </div>
      </div>
      <div className="flex items-center mb-4">
        <div className="w-10 h-10 bg-blue-600 flex items-center justify-center rounded-full">
          <i className="fas fa-lock text-white"></i>
        </div>
        <div className="ml-2 flex-grow">
          <input
            type="password"
            placeholder="Contraseña"
            id="password"
            name="password"
            className="input-login w-full"
            value={password}
            onChange={handlePasswordChange}
          />
        </div>
      </div>

      <div id="error" className="mb-4">
        {error && <p>{error}</p>}
      </div>
      <div className={'flex flex-col'}>
        <a href="#" className="text-black text-base">
          {'Olvidaste tu contraseña?'}
        </a>

        <button className={'btn-login'}>{'Iniciar Sesión'}</button>
      </div>
    </form>
  </div>
</div>

  );
};

export default Login;
