FUNCTION authenticateUser(username, password):
  QUERY database WITH username AND password
  IF found RETURN True
  ELSE RETURN False

  /** vulnerabilidades 
   * 1.-no deberia usar directo las variables de usuername y password ya puede ser vulnerable ataques de inyecccion de independencia 
   * 2.-la contraseña no deberia compararse en texto plano 
   * 
   * planes de mitigacion 
   * 1.-usar prepared statements que separen la logica de la consulta 
   * 2.-guardar la contraseña encriptada 
   * 3.-poner un bloqueo despues de varios intentos fallidos para mitigar un ataque de fuerza bruta 
   */

//mejora 
FUNCTION authenticateUser(username, password):
  HASHED_PASSWORD = hash(password)
  QUERY database WITH prepared statement:
    "SELECT * FROM users WHERE username = ? AND password = ?", (username, HASHED_PASSWORD)
  IF found THEN RETURN True
  ELSE RETURN False
*******************************************************************************************************************************************************************




  DEFINE FUNCTION generateJWT(userCredentials):
  IF validateCredentials(userCredentials):
    SET tokenExpiration = currentTime + 3600 // Token expires in one hour
    RETURN encrypt(userCredentials + tokenExpiration, secretKey)
  ELSE:
    RETURN error

/**
 * el metodo de validateCredentials debe ser un metodo mas robusto que asegure la autenticacion 
 * es necesario que el token contenga informacion como el usuario la ips si es el caso fecha de expiracion y 
 * los permisos que tiene para consumir ciertos apis o clases o metodos de un sistema 
 * 
 * 
 */



    //mejora 
    public class JWTGenerator {
        public static String generateJWT(UserCredentials userCredentials) {
            if (validateCredentials(userCredentials)) {
                long nowMillis = System.currentTimeMillis();
                Date now = new Date(nowMillis);
    
                byte[] secretKey = getSecretKey(); // Obtén la clave secreta de forma segura
    
                return Jwts.builder()
                        .setSubject(userCredentials.getUserId())  // Identificador del usuario
                        .claim("usuario", userCredentials.getUsuario()) 
                        .claim("ip", userCredentials.getIp()) 
                        .claim("roles", userCredentials.getRoles()) 
                        .claim("fechaExp", userCredentials.getFechaExpiration()) 
                        .setIssuedAt(now)
                        .setExpiration(new Date(nowMillis + 15000)) // 15 segundos ya que no es necesario tanto tiempo 
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact();
            } else {
                throw new RuntimeException("Invalid Credentials");
            }
        }
    }



*******************************************************************************************************************************************************************
   



PLAN secureDataCommunication:
  IMPLEMENT SSL/TLS for all data in transit
  USE encrypted storage solutions for data at rest
  ENSURE all data exchanges comply with HTTPS protocols


  /**
   * Nos aseguramos que los paquetes sean interceptados o intervenidos 
   * los certificados se deben firmar con una entidad certificada 
   * manteber las versiones mas actuales 
   * 
   * El cifrado de datos en reposo asegura que incluso si los discos físicos o las bases de datos son comprometidos, 
   * los datos no puedan ser leídos sin autorización.
   * 
   * Cifrado de Disco/Archivo: Aplica cifrado en niveles de disco o archivo, utilizando herramientas o servicios nativos de tu sistema operativo o proveedores de servicios en la nube.
Gestión de Claves: Utiliza un sistema de gestión de claves (KMS) para manejar las claves de cifrado de manera segura.
Acceso Controlado: Implementa políticas de acceso robustas para asegurar que solo el personal autorizado pueda acceder o desencriptar datos.
   */
