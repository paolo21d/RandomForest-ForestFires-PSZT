# RandomForest-ForestFires-PSZT
Predicting fires in the forest using random forest algorithm

Podstawy Sztucznej Inteligencji [PSZT]<br/>
Projekt 2: MM.DD.R3 Random Forest <br/>

<b>Authors</b><br/>
- Paweł Ś. - paolo21d
- Grzegorz A. - Quazan

<b>Opis:</b><br>
Zaimplementować algorytm lasu losowego (ang. Random Forest) do predykcji. <br/>
Zbiór danych do użycia: Forest Fires - https://archive.ics.uci.edu/ml/datasets/Forest+Fires. <br/>
Uzyskane rezultaty porównać z wynikami dla wybranej implementacji algorytmu ML z dostępnych bibliotek np. Scikit-learn, WEKA, MLlib, Tensorflow/Keras etc.

<b>Podstawowe problemy:<b>
1) Wczytanie danych w sposób możliwie efektywny do przetwarzania
2) Implementacja tworzenia lasu losowego
    - reprezentacja lasu - zbioru drzew decyzyjnych w pamięci komputera
    - budowa drzewa decyzyjnego
    - implementacja przycinania
3) Rozwiązania zadania z wykorzystaniem wybranej implementacji algorytmu ML z dostępnych bibliotek
4) Prównanie jakości wyników naszego modelu z gotowymi modelami

<b>Budowanie projektu:<b>
- do budowania został użyty maven
- mvn clean install
    