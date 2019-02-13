# uala-movies

Hay una debilidad en el recurso que esta viendo actualmente deberia ser una lista de recursos y no un solo recurso
ya que el usuario puede abrir multiples sesiones a la vez

No se programo la finalizacion de sesion

Esta pendiente la validacion de si un año de antiguedad de 10 años ya que no se aclara si se deben tomar la fecha completa incluyendo dias o simplemente con el año de lanzamiento basta.

No se construyo la parte de sugerencias del systema
Pero seria facil simpelemnte recorriendo la una lista de recursos disponibles y usando el metodo de usuario isInterestedIn(Resource r)

Personalmente prefiero mas una arquitectura de capas pero en este caso la estructura fue fluyendo mediante discovery, ya que esta es una aplicacion de pruebas.
