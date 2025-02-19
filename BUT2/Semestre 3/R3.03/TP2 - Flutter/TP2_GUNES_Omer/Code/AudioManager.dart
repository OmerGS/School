import 'package:audioplayers/audioplayers.dart';
import 'dart:math';

class AudioManager {
  final AudioPlayer player = AudioPlayer();

  Future<int> playRandomNote() async {
    int note = Random().nextInt(6) + 1;
    await playSpecificSound(note);
    await Future.delayed(Duration(seconds: 2));
    return note;
  }

  Future<int> playSpecificSound(int note) async {
    String filePath;

    switch (note) {
      case 1:
        filePath = 'assets/music/do.mp3';
        break;
      case 2:
        filePath = 'assets/music/re.mp3';
        break;
      case 3:
        filePath = 'assets/music/mi.mp3';
        break;
      case 4:
        filePath = 'assets/music/sol.mp3';
        break;
      case 5:
        filePath = 'assets/music/la.mp3';
        break;
      case 6:
        filePath = 'assets/music/si.mp3';
        break;
      default:
        filePath = 'assets/music/do.mp3';
    }

    try {
      await player.play(UrlSource(filePath));
      return note;
    } catch (e) {
      print('Erreur lors de la lecture du son : $e');
      return -1;
    }
  }
}