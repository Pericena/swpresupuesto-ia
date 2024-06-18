describe('Prueba de Login', () => {
  beforeEach(() => {
    // Configuración de interceptación de la solicitud POST al endpoint de login
    cy.intercept('POST', 'http://167.99.63.51:27016/api/usuarios/Login', (req) => {
      // Simular una respuesta exitosa con un token válido
      req.reply({
        statusCode: 200,
        body: {
          token: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMkBnbWFpbC5jb20iLCJpYXQiOjE3MTg0ODc1NTIsImV4cCI6MTcxODQ5MTE1Mn0.orNVHU0z9BCAWwOXlBpKskQ62vogH-jHKojPTNZ2CeVciAkt9IdoBYRjMULLBsDcDq4LwX5s7lWR3uZq-3p-BQ'
        }
      });
    });

    // Visitar la página de login antes de cada prueba
    cy.visit('http://localhost:4321'); // Ajustar la URL según tu entorno
  });

  it('Realizar login exitoso y verificar redirección a /admin', () => {
    // Establecer viewport para simular una pantalla pequeña (ancho de 600px)
    cy.viewport(1200, 1000);

    // Llenar y enviar el formulario de login
    cy.get('input[name="email"]').type('user2@example.com');
    cy.get('input[name="password"]').type('12345678');
    cy.get('.btn2').click(); // Ajustar el selector del botón según tu página

    // Esperar a que ocurra la redirección a /admin y verificar URL
    cy.url().should('include', '/admin');

    // Asegurar que el token se haya almacenado en localStorage después de la redirección
    cy.window()
      .its('localStorage.token')
      .should('exist')
      .and('not.be.empty'); // Asegurar que el token exista y no esté vacío

    // Verificar que elementos específicos de la página de administración estén presentes
    // cy.contains('Bienvenido a la página de administración');
  });

  it('Mostrar mensaje de error si se intenta iniciar sesión con credenciales incorrectas', () => {
    // Interceptación para simular un error 401 (no autorizado)
    cy.intercept('POST', 'http://167.99.63.51:27016/api/usuarios/Login', (req) => {
      req.reply({
        statusCode: 401,
        body: { error: 'Credenciales inválidas' }
      });
    });

    // Llenar y enviar el formulario de login con credenciales incorrectas
    cy.get('input[name="email"]').type('user2@example.com');
    cy.get('input[name="password"]').type('12345678');
    cy.get('.btn2').click(); // Ajustar el selector del botón según tu página

    // Verificar que se muestre el mensaje de error en pantalla
    // cy.get('.error-message').should('contain.text', 'Credenciales inválidas');
  });

  // Realizar más pruebas según sea necesario para cubrir diferentes casos y escenarios
});
