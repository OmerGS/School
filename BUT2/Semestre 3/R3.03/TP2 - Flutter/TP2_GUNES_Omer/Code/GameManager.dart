import 'dart:async';
import 'AudioManager.dart';

class GameManager {
  final List<int> sequence = [];
  final List<int> userInput = [];
  final AudioManager audio = AudioManager();
  final StreamController<int> _noteStreamController = StreamController<int>.broadcast();

  Stream<int> get noteStream => _noteStreamController.stream;

  Future<void> startRound(int difficultyLevel) async {
    sequence.clear();
    userInput.clear();

    int sequenceLength;
    switch (difficultyLevel) {
      case 0:
        sequenceLength = 3;
        break;
      case 1:
        sequenceLength = 6;
        break;
      case 2:
        sequenceLength = 9;
        break;
      default:
        sequenceLength = 3;
    }

    for (int i = 0; i < sequenceLength; i++) {

      int note = await audio.playRandomNote();
      sequence.add(note);
      _noteStreamController.add(note);

      _noteStreamController.add(-1);
    }
  }

  void playNote(int note)  {
    audio.playSpecificSound(note);
  }


  bool checkUserInput(int note) {
    userInput.add(note);
    int currentIndex = userInput.length - 1;

    if (sequence[currentIndex] != note) {
      return false;
    }

    if (userInput.length == sequence.length) {
      return true;
    }
    return true;
  }
}