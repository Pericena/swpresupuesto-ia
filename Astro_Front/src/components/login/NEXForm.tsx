import React, { useState } from 'react';

interface LoginFormProps {
  onLoginSuccess: () => void;
}

const LoginForm: React.FC<LoginFormProps> = ({ onLoginSuccess }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (!email || !password) {
      setError('Por favor, complete todos los campos.');
      return;
    }

    try {
      const response = await fetch('http://167.99.63.51:27016/api/usuarios/Login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, password })
      });

      if (!response.ok) {
        throw new Error('Error al iniciar sesión');
      }

      const data = await response.json();
      const token = data.token as string; // Asumiendo que el servidor devuelve un objeto con un campo 'token'

      // Guardar el token en el almacenamiento local (localStorage)
      localStorage.setItem('token', token);

      // Mostrar alerta de éxito
      //alert('¡Inicio de sesión exitoso!');
      // Redireccionar a la página de administración (por ejemplo, '/admin')
      window.location.href = '/admin'; 
      // Llamar a la función de éxito proporcionada por el padre
      onLoginSuccess();

    } catch (error) {
      console.error('Error al iniciar sesión:', error);
      setError('Error al iniciar sesión. Por favor, inténtelo de nuevo.');
    }
  };

  return (
    <form method="POST" className="sign-in-form" onSubmit={handleSubmit}>
      <h2 className="title">Iniciar Sesión</h2>
      {error && <p className="error-message">{error}</p>}
      <div className="input-field">
        <i className="fas fa-user"></i>
        <input
          id="email"
          type="email"
          name="email"
          required
          autoComplete="email"
          autoFocus
          placeholder="Correo"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>
      <div className="input-field">
        <i className="fas fa-lock"></i>
        <input
          id="password"
          type="password"
          name="password"
          required
          autoComplete="current-password"
          placeholder="Contraseña"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <button type="submit" id='btn2' className="btn2">Ingresar</button>
    </form>
  );
};

export default LoginForm;
