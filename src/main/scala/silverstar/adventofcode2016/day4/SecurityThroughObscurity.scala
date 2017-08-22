package silverstar.adventofcode2016.day4

/**
  * --- Day 4: Security Through Obscurity ---
  * Finally, you come across an information kiosk with a list of rooms. Of course, the list is encrypted and full of
  * decoy data, but the instructions to decode the list are barely hidden nearby. Better remove the decoy data first.
  * Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID,
  * and a checksum in square brackets.
  * A room is real (not a decoy) if the checksum is the five most common letters in the encrypted name, in order,
  * with ties broken by alphabetization.
  * For example:
  * - aaaaa-bbb-z-y-x-123[abxyz] is a real room because the most common letters are a (5), b (3), and then a tie
  *   between x, y, and z, which are listed alphabetically.
  * - a-b-c-d-e-f-g-h-987[abcde] is a real room because although the letters are all tied (1 of each), the first five
  *   are listed alphabetically.
  * - not-a-real-room-404[oarel] is a real room.
  * - totally-real-room-200[decoy] is not.
  * Of the real rooms from the list above, the sum of their sector IDs is 1514.
  * What is the sum of the sector IDs of the real rooms?
  */
object SecurityThroughObscurity {

  def sumOfTheSectorIdsForTheRealRooms(s: String): Long =
    s.split("\r\n").toList.map(parseRoom).filter(_.isRealRoom).foldLeft(0L)(_ + _.sectorId)

  def parseRoom(s: String): RoomEncryption = {
    val split = s.split("\\W+")
    RoomEncryption(split.init.init.mkString, split.init.last.toLong, split.last)
  }

  case class RoomEncryption(encryptedName: String,
                            sectorId: Long,
                            checksum: String) {
    def calculateChecksum: String = encryptedName.distinct.map(c => (c, encryptedName.count(_ == c))).sortBy {
      case (char, count) => (-count, char)
    }.map(_._1).take(5).mkString

    def isRealRoom: Boolean = calculateChecksum == checksum
  }
}
