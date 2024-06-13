// LoginForm.jsx
import React from 'react';
import { gql, useMutation } from '@apollo/client';
import NEXForm from './NEXForm.astro'; // Importa el componente Astro

const LOGIN_MUTATION = gql`
  mutation Login($email: String!, $password: String!) {
    login(email: $email, password: $password) {
      token
    }
  }
`;

const LoginForm = () => {
  const [login, { data, error }] = useMutation(LOGIN_MUTATION);

  const handleSubmit = async (formData) => {
    const { email, password } = formData;
    try {
      const response = await login({
        variables: {
          email,
          password,
        },
      });
      console.log('Login successful:', response.data);
      // Aquí puedes manejar el token de autenticación y redireccionar al usuario
    } catch (error) {
      console.error('Login error:', error);
      // Manejar errores de autenticación
    }
  };

  return (
    <div>
      <NEXForm onFormSubmit={handleSubmit} />
    </div>
  );
};

export default LoginForm;
