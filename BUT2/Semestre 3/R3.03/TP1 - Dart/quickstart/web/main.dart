import 'dart:html' as web;

void main() {
  bool isPlaying = false;

  final audio = web.AudioElement();

  final buttonSon1 = web.document.querySelector('#buttonSon1') as web.ButtonElement;
  final buttonSon2 = web.document.querySelector('#buttonSon2') as web.ButtonElement;
  final buttonSon3 = web.document.querySelector('#buttonSon3') as web.ButtonElement;
  final buttonSon4 = web.document.querySelector('#buttonSon4') as web.ButtonElement;
  final buttonSon5 = web.document.querySelector('#buttonSon5') as web.ButtonElement;

  final output = web.document.querySelector('#output') as web.DivElement;


  // SON N°1


  buttonSon1.onClick.listen((event) {
    audio.src = 'son1.wav'; 
    audio.preload = 'auto';
    
    if (isPlaying) {
      audio.pause();
      output.text = 'La musique est en pause.';
    } else {
      audio.play();
      output.text = 'La musique joue.';
    }
    isPlaying = !isPlaying;
  });

  audio.onLoadedData.listen((_) {
    output.text = 'Audio prêt à jouer.';
  });

  audio.onError.listen((event) {
    output.text = 'Erreur lors du chargement de l\'audio.';
  });




  // SON N°2

  buttonSon2.onClick.listen((event) {
    audio.src = 'son2.wav'; 
    audio.preload = 'auto';

    if (isPlaying) {
      audio.pause();
      output.text = 'La musique est en pause.';
    } else {
      audio.play();
      output.text = 'La musique joue.';
    }
    isPlaying = !isPlaying;
  });

  audio.onLoadedData.listen((_) {
    output.text = 'Audio prêt à jouer.';
  });

  audio.onError.listen((event) {
    output.text = 'Erreur lors du chargement de l\'audio.';
  });



  // SON N°3

  buttonSon3.onClick.listen((event) {
    audio.src = 'son3.wav'; 
    audio.preload = 'auto';

    if (isPlaying) {
      audio.pause();
      output.text = 'La musique est en pause.';
    } else {
      audio.play();
      output.text = 'La musique joue.';
    }
    isPlaying = !isPlaying;
  });

  audio.onLoadedData.listen((_) {
    output.text = 'Audio prêt à jouer.';
  });

  audio.onError.listen((event) {
    output.text = 'Erreur lors du chargement de l\'audio.';
  });





  // SON N°4


  buttonSon4.onClick.listen((event) {
    audio.src = 'son4.wav'; 
    audio.preload = 'auto';

    if (isPlaying) {
      audio.pause();
      output.text = 'La musique est en pause.';
    } else {
      audio.play();
      output.text = 'La musique joue.';
    }
    isPlaying = !isPlaying;
  });

  audio.onLoadedData.listen((_) {
    output.text = 'Audio prêt à jouer.';
  });

  audio.onError.listen((event) {
    output.text = 'Erreur lors du chargement de l\'audio.';
  });



  // SON N°5


  buttonSon5.onClick.listen((event) {
    audio.src = 'son5.wav'; 
    audio.preload = 'auto';

    if (isPlaying) {
      audio.pause();
      output.text = 'La musique est en pause.';
    } else {
      audio.play();
      output.text = 'La musique joue.';
    }
    isPlaying = !isPlaying;
  });

  audio.onLoadedData.listen((_) {
    output.text = 'Audio prêt à jouer.';
  });

  audio.onError.listen((event) {
    output.text = 'Erreur lors du chargement de l\'audio.';
  });


}
