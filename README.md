# consumption-converter
![Screenshot](logo_converter.jpeg)
Custom Control for WindparksFX

# Gebrauch

**Package**

Zum zunächst unser Custom Control zu implementieren müssen Sie das consumption-converter Projekt herunterladen. Kopieren Sie den Gesamten <code>consumption_converter</code> Ordner.

**UI Converter Dashboard**

Um dieses Custom Control zu brauchen, benötigen Sie die Klasse <code>ConverterControl.java</code>

**Business control slider**

Um dieses Custom Business Control zu brauchen, benötigen Sie die Klasse <code>SliderControl.java</code>

# Implementieren

Wählen Sie zunächst welches Custom Control Sie implementieren möchten. (Converter oder Slider)
Danach begeben Sie sich in Ihre gewünschte Klasse wo Sie das UI plazieren möchten.

** Converter Control **
1. Fügen Sie zunächste eine Deklaration <code>private ConverterControl convertercontrol;</code> ein.
2. Im berreich <code>private void initializeParts()</code> initialisieren Sie den Converter mit <code> convertercontrol = new ConverterControl(); </code>
3. Nun müssen wir die Bindings anpassen. Ersetzen Sie die herkömmliche <code>textProperty()</code> mit unseren Properties.
Diese können wie gefolgt aussehen.
Beispiel: 
<code>      businessControl.firstYearProperty().bindBidirectional(model.getWindparkProxy().production2015MwhProperty()); </code> <br>
    <code>  businessControl.secondYearProperty().bind(model.getWindparkProxy().production2016MwhProperty());</code>
   <code>     businessControl.thirdYearProperty().bind(model.getWindparkProxy().production2017MwhProperty());</code>
     <code>   businessControl.fourtfhYearProperty().bind(model.getWindparkProxy().production2018MwhProperty()); </code>
 
 Unsere Properties stehen für die 4 detallierte Jahre. 
 Produktionsjahr 2015 -> firstYearProperty()
 Produktionsjahr 2016 -> secondYearProperty()
 Produktionsjahr 2017 -> thirdYearProperty()
 Produktionsjahr 2018 -> fourtYearProperty()
