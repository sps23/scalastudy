package scala.collections.arrays

object arrays {
  println("Welcome to the Scala worksheet - arrays")
                                                  //> Welcome to the Scala worksheet - arrays

  ArrayFixedLength.nums                           //> res0: Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

  ArrayFixedLength.words                          //> res1: Array[String] = Array(My, name, is, Sylwester, null, null, null, null,
                                                  //|  null, null)

  ArrayFixedLength.stringArray                    //> res2: Array[String] = Array(Hello, World)
  
  ArrayVariableLength.nums1                       //> res3: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 1, 2, 3, 5)
                                                  //| 

  ArrayVariableLength.nums2                       //> res4: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(8, 13, 21, 13,
                                                  //|  56, 63)

  ArrayVariableLength.nums3                       //> res5: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 8, 8, 8,
                                                  //|  0)

  ArrayVariableLength.numsArray                   //> res6: Array[Int] = Array(1, 1, 2, 3, 5)
 
  ArrayVariableLength.numsBuffer                  //> res7: scala.collection.mutable.Buffer[Int] = ArrayBuffer(9, 9, 9)

  ArrayTraversing.printlnArrayBuffer(ArrayTraversing.numsArrayBuffer)
                                                  //> 0: 7
                                                  //| 1: 8
                                                  //| 2: 9
                                                  //| 3: 10

  ArrayTraversing.printArrayBuffer(ArrayTraversing.numsArrayBuffer)
                                                  //> 7; 8; 9; 10; 

  ArrayTraversing.printlnArray(ArrayTraversing.numsArray)
                                                  //> 0: 1
                                                  //| 1: 2
                                                  //| 2: 3
                                                  //| 3: 4
                                                  //| 4: 5

  ArrayTraversing.printArray(ArrayTraversing.numsArray)
                                                  //> 1; 2; 3; 4; 5; 

  ArrayTraversing.numsArray                       //> res8: Array[Int] = Array(1, 2, 3, 4, 5)
  ArrayTraversing.numsArrayBuffer                 //> res9: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(7, 8, 9, 10)

  ArrayTransforming.numsArray1                    //> res10: Array[Int] = Array(1, 2, 3, 4)
  ArrayTransforming.arrayDouble(ArrayTransforming.numsArray1)
                                                  //> res11: Array[Int] = Array(2, 4, 6, 8)
  ArrayTransforming.numsArray1                    //> res12: Array[Int] = Array(1, 2, 3, 4)

  ArrayTransforming.numsArray2                    //> res13: Array[Int] = Array(2, -4, 7, -5)
  ArrayTransforming.arrayDouble(ArrayTransforming.numsArray2)
                                                  //> res14: Array[Int] = Array(4, -8, 14, -10)
  ArrayTransforming.numsArray2                    //> res15: Array[Int] = Array(2, -4, 7, -5)

  ArrayTransforming.arrayDoubleEvenGourd(ArrayTransforming.numsArray1)
                                                  //> res16: Array[Int] = Array(4, 8)
  ArrayTransforming.arrayDoubleEvenMap(ArrayTransforming.numsArray1)
                                                  //> res17: Array[Int] = Array(4, 8)
  ArrayTransforming.arrayDoubleEvenGourd(ArrayTransforming.numsArray2)
                                                  //> res18: Array[Int] = Array(4, -8)
  ArrayTransforming.arrayDoubleEvenMap(ArrayTransforming.numsArray2)
                                                  //> res19: Array[Int] = Array(4, -8)
  
  ArrayTransforming.removeAllAfterFirstNegativeInefficient(ArrayTransforming.numsArrayBuffer1a)
                                                  //> ab = ArrayBuffer(-1, 2, 3, 4, -5)
                                                  //| ab = ArrayBuffer(-1)
  ArrayTransforming.removeAllAfterFirstNegativeEfficient1(ArrayTransforming.numsArrayBuffer1b)
                                                  //> ab = ArrayBuffer(-1, 2, 3, 4, -5)
                                                  //| ab = ArrayBuffer(-1)
  ArrayTransforming.removeAllAfterFirstNegativeEfficient2(ArrayTransforming.numsArrayBuffer1c)
                                                  //> ab = ArrayBuffer(-1, 2, 3, 4, -5)
                                                  //| n = 0; indexes = Vector(0, 4)
                                                  //| indexes2 = Vector(4, 0)
                                                  //| ab = ArrayBuffer(2, 4, -5)
 
  ArrayTransforming.removeAllAfterFirstNegativeInefficient(ArrayTransforming.numsArrayBuffer2a)
                                                  //> ab = ArrayBuffer(1, 2, 3, 4, -5)
                                                  //| ab = ArrayBuffer(1, 2, 3, 4, -5)
  ArrayTransforming.removeAllAfterFirstNegativeEfficient1(ArrayTransforming.numsArrayBuffer2b)
                                                  //> ab = ArrayBuffer(1, 2, 3, 4, -5)
                                                  //| ab = ArrayBuffer(1, 2, 3, 4, -5)
  ArrayTransforming.removeAllAfterFirstNegativeEfficient2(ArrayTransforming.numsArrayBuffer2c)
                                                  //> ab = ArrayBuffer(1, 2, 3, 4, -5)
                                                  //| n = -1; indexes = Vector(4)
                                                  //| indexes2 = Vector(4)
                                                  //| ab = ArrayBuffer(2, 3, 4, -5)

  ArrayTransforming.removeAllAfterFirstNegativeInefficient(ArrayTransforming.numsArrayBuffer3a)
                                                  //> ab = ArrayBuffer(1, 2, 3, -4, -5)
                                                  //| ab = ArrayBuffer(1, 2, 3, -4)
  ArrayTransforming.removeAllAfterFirstNegativeEfficient1(ArrayTransforming.numsArrayBuffer3b)
                                                  //> ab = ArrayBuffer(1, 2, 3, -4, -5)
                                                  //| ab = ArrayBuffer(1, 2, 3, -4)
  ArrayTransforming.removeAllAfterFirstNegativeEfficient2(ArrayTransforming.numsArrayBuffer3c)
                                                  //> ab = ArrayBuffer(1, 2, 3, -4, -5)
                                                  //| n = 0; indexes = Vector(3, 4)
                                                  //| indexes2 = Vector(4, 3)
                                                  //| ab = ArrayBuffer(2, -4, -5)

  ArrayTransforming.removeAllAfterFirstNegativeInefficient(ArrayTransforming.numsArrayBuffer4a)
                                                  //> ab = ArrayBuffer(1, 2, -3, 4, -5)
                                                  //| ab = ArrayBuffer(1, 2, -3)
  ArrayTransforming.removeAllAfterFirstNegativeEfficient1(ArrayTransforming.numsArrayBuffer4b)
                                                  //> ab = ArrayBuffer(1, 2, -3, 4, -5)
                                                  //| ab = ArrayBuffer(1, 2, -3)
  ArrayTransforming.removeAllAfterFirstNegativeEfficient2(ArrayTransforming.numsArrayBuffer4c)
                                                  //> ab = ArrayBuffer(1, 2, -3, 4, -5)
                                                  //| n = 0; indexes = Vector(2, 4)
                                                  //| indexes2 = Vector(4, 2)
                                                  //| ab = ArrayBuffer(2, 4, -5)

  ArrayAlgorithms.sum(ArrayAlgorithms.num1)       //> res20: Int = 318
  ArrayAlgorithms.sum(ArrayAlgorithms.num1)       //> res21: Int = 318

  ArrayAlgorithms.sortArray(ArrayAlgorithms.num1) //> res22: Array[Int] = Array(1, 4, 6, 32, 44, 55, 78, 98)
  ArrayAlgorithms.sortArray(ArrayAlgorithms.num2, false)
                                                  //> res23: Array[Int] = Array(98, 78, 55, 44, 32, 6, 4, 1)
  ArrayAlgorithms.sortArrayBuffer(ArrayAlgorithms.num3, false)
                                                  //> res24: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(98, 78, 55, 
                                                  //| 44, 32, 6, 4, 1)

  ArrayAlgorithms.sortInPlace(ArrayAlgorithms.num1)
                                                  //> [I@30a16748

  ArrayAlgorithms.extremum(ArrayAlgorithms.string)//> res25: String = little
  ArrayAlgorithms.extremum(ArrayAlgorithms.string, false)
                                                  //> res26: String = Mary

  ArrayAlgorithms.printlnArray(ArrayAlgorithms.num1)
                                                  //> 1 and 4 and 6 and 32 and 44 and 55 and 78 and 98
  ArrayAlgorithms.printlnArray(ArrayAlgorithms.num1, 1)
                                                  //> 1;4;6;32;44;55;78;98
  ArrayAlgorithms.printlnArray(ArrayAlgorithms.num1, 2)
                                                  //> [1;4;6;32;44;55;78;98]
  ArrayAlgorithms.printlnArray(ArrayAlgorithms.num1, 3)
                                                  //> [I@30a16748
 
  Exercises.randomArray(7)                        //> res27: Array[Int] = Array(2, 2, 4, 4, 6, 2, 5)

  Exercises.swapAdjInPlaceIter(Array(1, 2, 3, 4, 5))
                                                  //> a = [2;1;4;3;5]
  Exercises.swapAdjInPlaceRec(Array(1, 2, 3, 4, 5))
                                                  //> a = [2;1;4;3;5]
  Exercises.swapAdjInPlaceIter(new Array[Int](0)) //> a = []
  Exercises.swapAdjInPlaceRec(new Array[Int](0))  //> a = []
     
  Exercises.swapAdjInPlaceIter(Array(5))          //> a = [5]
  Exercises.swapAdjInPlaceRec(Array(5))           //> a = [5]
  
  Exercises.swapAdjInPlaceIter(Array(1, 2, 3, 4)) //> a = [2;1;4;3]
  Exercises.swapAdjInPlaceRec(Array(1, 2, 3, 4))  //> a = [2;1;4;3]

  Exercises.swapAdjIter(Array(1, 2, 3, 4, 5))     //> res28: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(2, 1, 4, 3, 
                                                  //| 5)
  Exercises.swapAdjRec(Array(1, 2, 3, 4, 5))      //> res29: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(2, 1, 4, 3, 
                                                  //| 5)
    
  Exercises.swapAdjIter(Array(1, 2, 3, 4))        //> res30: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(2, 1, 4, 3)
  Exercises.swapAdjRec(Array(1, 2, 3, 4))         //> res31: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(2, 1, 4, 3)
  
  Exercises.swapAdjIter(Array(5))                 //> res32: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(5)
  Exercises.swapAdjRec(Array(5))                  //> res33: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(5)
  
  Exercises.swapAdjIter(new Array[Int](0))        //> res34: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
  Exercises.swapAdjRec(new Array[Int](0))         //> res35: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
  
  Exercises.separetaPosNegIter(Array(1,-1,2,-2,0,3))
                                                  //> res36: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, -1,
                                                  //|  -2, 0)
  Exercises.separetaPosNegIter(Array(0))          //> res37: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(0)
  Exercises.separetaPosNegIter(Array(-1))         //> res38: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(-1)
  Exercises.separetaPosNegIter(Array(1))          //> res39: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1)
  Exercises.separetaPosNegIter(Array(-5,0,1,-1,2,-2,0,3))
                                                  //> res40: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, -5,
                                                  //|  0, -1, -2, 0)
  
  Exercises.withoutDuplicates(Array(7,8,7,1,2,3,3,2,1,4,5))
                                                  //> res41: Array[Int] = Array(7, 8, 1, 2, 3, 4, 5)
  
  java.util.TimeZone.getAvailableIDs()            //> res42: Array[String] = Array(Etc/GMT+12, Etc/GMT+11, Pacific/Midway, Pacifi
                                                  //| c/Niue, Pacific/Pago_Pago, Pacific/Samoa, US/Samoa, America/Adak, America/A
                                                  //| tka, Etc/GMT+10, HST, Pacific/Honolulu, Pacific/Johnston, Pacific/Rarotonga
                                                  //| , Pacific/Tahiti, SystemV/HST10, US/Aleutian, US/Hawaii, Pacific/Marquesas,
                                                  //|  AST, America/Anchorage, America/Juneau, America/Nome, America/Sitka, Ameri
                                                  //| ca/Yakutat, Etc/GMT+9, Pacific/Gambier, SystemV/YST9, SystemV/YST9YDT, US/A
                                                  //| laska, America/Dawson, America/Ensenada, America/Los_Angeles, America/Metla
                                                  //| katla, America/Santa_Isabel, America/Tijuana, America/Vancouver, America/Wh
                                                  //| itehorse, Canada/Pacific, Canada/Yukon, Etc/GMT+8, Mexico/BajaNorte, PST, P
                                                  //| ST8PDT, Pacific/Pitcairn, SystemV/PST8, SystemV/PST8PDT, US/Pacific, US/Pac
                                                  //| ific-New, America/Boise, America/Cambridge_Bay, America/Chihuahua, America/
                                                  //| Creston, America/Dawson_Creek, America/Denver, America/Edmonton, America/He
                                                  //| rmosillo, America/Inuvi
                                                  //| Output exceeds cutoff limit.

  Exercises.getTimeZones("America")               //> res43: Array[String] = Array(Adak, Anchorage, Anguilla, Antigua, Araguaina,
                                                  //|  Argentina/Buenos_Aires, Argentina/Catamarca, Argentina/ComodRivadavia, Arg
                                                  //| entina/Cordoba, Argentina/Jujuy, Argentina/La_Rioja, Argentina/Mendoza, Arg
                                                  //| entina/Rio_Gallegos, Argentina/Salta, Argentina/San_Juan, Argentina/San_Lui
                                                  //| s, Argentina/Tucuman, Argentina/Ushuaia, Aruba, Asuncion, Atikokan, Atka, B
                                                  //| ahia, Bahia_Banderas, Barbados, Belem, Belize, Blanc-Sablon, Boa_Vista, Bog
                                                  //| ota, Boise, Buenos_Aires, Cambridge_Bay, Campo_Grande, Cancun, Caracas, Cat
                                                  //| amarca, Cayenne, Cayman, Chicago, Chihuahua, Coral_Harbour, Cordoba, Costa_
                                                  //| Rica, Creston, Cuiaba, Curacao, Danmarkshavn, Dawson, Dawson_Creek, Denver,
                                                  //|  Detroit, Dominica, Edmonton, Eirunepe, El_Salvador, Ensenada, Fort_Wayne, 
                                                  //| Fortaleza, Glace_Bay, Godthab, Goose_Bay, Grand_Turk, Grenada, Guadeloupe, 
                                                  //| Guatemala, Guayaquil, Guyana, Halifax, Havana, Hermosillo, Indiana/Indianap
                                                  //| olis, Indiana/Knox, Ind
                                                  //| Output exceeds cutoff limit.
  Exercises.getTimeZones("Canada")                //> res44: Array[String] = Array(Atlantic, Central, East-Saskatchewan, Eastern,
                                                  //|  Mountain, Newfoundland, Pacific, Saskatchewan, Yukon)
  Exercises.getTimeZones("")                      //> res45: Array[String] = Array(ACT, AET, AGT, ART, AST, Africa/Abidjan, Afric
                                                  //| a/Accra, Africa/Addis_Ababa, Africa/Algiers, Africa/Asmara, Africa/Asmera, 
                                                  //| Africa/Bamako, Africa/Bangui, Africa/Banjul, Africa/Bissau, Africa/Blantyre
                                                  //| , Africa/Brazzaville, Africa/Bujumbura, Africa/Cairo, Africa/Casablanca, Af
                                                  //| rica/Ceuta, Africa/Conakry, Africa/Dakar, Africa/Dar_es_Salaam, Africa/Djib
                                                  //| outi, Africa/Douala, Africa/El_Aaiun, Africa/Freetown, Africa/Gaborone, Afr
                                                  //| ica/Harare, Africa/Johannesburg, Africa/Juba, Africa/Kampala, Africa/Kharto
                                                  //| um, Africa/Kigali, Africa/Kinshasa, Africa/Lagos, Africa/Libreville, Africa
                                                  //| /Lome, Africa/Luanda, Africa/Lubumbashi, Africa/Lusaka, Africa/Malabo, Afri
                                                  //| ca/Maputo, Africa/Maseru, Africa/Mbabane, Africa/Mogadishu, Africa/Monrovia
                                                  //| , Africa/Nairobi, Africa/Ndjamena, Africa/Niamey, Africa/Nouakchott, Africa
                                                  //| /Ouagadougou, Africa/Porto-Novo, Africa/Sao_Tome, Africa/Timbuktu, Africa/T
                                                  //| ripoli, Africa/Tunis, A
                                                  //| Output exceeds cutoff limit.
  Exercises.getTimeZones()                        //> res46: Array[String] = Array(ACT, AET, AGT, ART, AST, Africa/Abidjan, Afric
                                                  //| a/Accra, Africa/Addis_Ababa, Africa/Algiers, Africa/Asmara, Africa/Asmera, 
                                                  //| Africa/Bamako, Africa/Bangui, Africa/Banjul, Africa/Bissau, Africa/Blantyre
                                                  //| , Africa/Brazzaville, Africa/Bujumbura, Africa/Cairo, Africa/Casablanca, Af
                                                  //| rica/Ceuta, Africa/Conakry, Africa/Dakar, Africa/Dar_es_Salaam, Africa/Djib
                                                  //| outi, Africa/Douala, Africa/El_Aaiun, Africa/Freetown, Africa/Gaborone, Afr
                                                  //| ica/Harare, Africa/Johannesburg, Africa/Juba, Africa/Kampala, Africa/Kharto
                                                  //| um, Africa/Kigali, Africa/Kinshasa, Africa/Lagos, Africa/Libreville, Africa
                                                  //| /Lome, Africa/Luanda, Africa/Lubumbashi, Africa/Lusaka, Africa/Malabo, Afri
                                                  //| ca/Maputo, Africa/Maseru, Africa/Mbabane, Africa/Mogadishu, Africa/Monrovia
                                                  //| , Africa/Nairobi, Africa/Ndjamena, Africa/Niamey, Africa/Nouakchott, Africa
                                                  //| /Ouagadougou, Africa/Porto-Novo, Africa/Sao_Tome, Africa/Timbuktu, Africa/T
                                                  //| ripoli, Africa/Tunis, A
                                                  //| Output exceeds cutoff limit.
  }