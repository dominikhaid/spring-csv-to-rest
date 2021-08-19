package kbbg;

/**
 * 404 Exception Message
 */
class KbbgNotFoundException extends RuntimeException {

  KbbgNotFoundException(Long id) {
    super("Kein Ergebniss für Betriebsstelle " + id + " !");
  }
}
