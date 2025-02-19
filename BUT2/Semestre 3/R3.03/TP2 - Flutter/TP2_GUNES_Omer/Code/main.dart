import 'package:flutter/material.dart';
import 'GameManager.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Jeu de Musique',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Jeu de Musique - Devinez la Note'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final GameManager game = GameManager();
  String feedback = '';
  bool isPlayingSequence = false;
  int highlightedNote = -1;
  List<bool> noteStatus = [];
  int score = 0;
  int maxNotes = 0;
  int currentNoteIndex = 0;

  @override
  void initState() {
    super.initState();
    game.noteStream.listen((note) {
      setState(() {
        highlightedNote = note;
      });
    });
  }

  void startGame(int difficulty) async {
    setState(() {
      feedback = 'Écoutez la séquence...';
      isPlayingSequence = true;
      score = 0;
      noteStatus = List.generate(difficulty == 0 ? 3 : difficulty == 1 ? 6 : 9, (index) => false);
      maxNotes = noteStatus.length;
      currentNoteIndex = 0;
    });

    await game.startRound(difficulty);

    setState(() {
      feedback = 'Rejouez la séquence!';
      isPlayingSequence = false;
    });
  }

  void onUserClick(int note) {
    if (isPlayingSequence) return;

    bool isCorrect = game.checkUserInput(note);

    if (isCorrect) {
      setState(() {
        feedback = "Correct";
        noteStatus[currentNoteIndex] = true;
        score++;
      });
    } else {
      setState(() {
        feedback = "Incorrect! Essayez encore !";
        noteStatus[currentNoteIndex] = false;
      });
    }

    currentNoteIndex++;

    if (currentNoteIndex == maxNotes) {
      setState(() {
        feedback = "Fin de partie! Score final: $score";
        isPlayingSequence = true;
      });
    }
  }

  void resetGame() {
    setState(() {
      feedback = '';
      isPlayingSequence = false;
      noteStatus = [];
      score = 0;
      currentNoteIndex = 0;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              feedback,
              style: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 20),
            if (!isPlayingSequence && feedback.isEmpty) ...[
              ElevatedButton(
                onPressed: isPlayingSequence ? null : () => startGame(0),
                child: const Text('Facile'),
              ),
              ElevatedButton(
                onPressed: isPlayingSequence ? null : () => startGame(1),
                child: const Text('Moyen'),
              ),
              ElevatedButton(
                onPressed: isPlayingSequence ? null : () => startGame(2),
                child: const Text('Difficile'),
              ),
            ],
            const SizedBox(height: 20),
            if (feedback.contains('Fin')) ...[
              ElevatedButton(
                onPressed: resetGame,
                child: const Text('Recommencer'),
              ),
            ],
            const SizedBox(height: 20),
            Wrap(
              spacing: 10,
              children: List.generate(noteStatus.length, (index) {
                bool isCurrentNote = highlightedNote == index + 1;
                return CircleAvatar(
                  radius: 20,
                  backgroundColor: isCurrentNote ? Colors.red : (noteStatus[index] ? Colors.green : Colors.grey),
                  child: Center(
                    child: Text(
                      (index + 1).toString(),
                      style: const TextStyle(color: Colors.white),
                    ),
                  ),
                );
              }),
            ),
            const SizedBox(height: 20),
            if (!isPlayingSequence) ...[
              Wrap(
                spacing: 10,
                children: List.generate(6, (index) {
                  int note = index + 1;
                  bool isHighlighted = highlightedNote == note;
                  return ElevatedButton(
                    onPressed: () {
                      onUserClick(note);
                      game.playNote(note);
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: isHighlighted
                          ? Colors.red 
                          : Colors.blue,
                    ),
                    child: Text(['Do', 'Ré', 'Mi', 'Sol', 'La', 'Si'][index]),
                  );
                }),
              ),
            ],
          ],
        ),
      ),
    );
  }
}

