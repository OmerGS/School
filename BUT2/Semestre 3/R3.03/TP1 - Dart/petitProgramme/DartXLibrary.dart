import 'package:dartx/dartx_io.dart';

void main(){
  print(''.isDouble); //Reponse attendu false
  print('a'.isDouble); //Reponse attendu false
  print('1'.isDouble); //Reponse attendu true
  print('1.0'.isDouble); // Reponse attendu true
  print('123456789.987654321'.isDouble); //Reponse attendu true
  print('1,000'.isDouble); //Reponse attendu false
}