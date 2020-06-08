# consumption-converter
![Screenshot](logo_converter.jpeg) <br>
Custom Control for WindparksFX
Dieses Custom Control bietet eine klare Übersicht, welche Geräte im Haushalt am meisten Strom im Jahr verbrauchen. Unser Converter nimmt den Jahresschnitt von den Geräten und teilt diese durch die erzeugte Enerige von den Windparks.

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

**Converter Control**
1. Fügen Sie zunächste eine Deklaration <code>private ConverterControl convertercontrol;</code> ein.
2. Im berreich <code>private void initializeParts()</code> initialisieren Sie den Converter mit <code> convertercontrol = new ConverterControl(); </code>
3. Nun müssen wir die Bindings anpassen. Ersetzen Sie die herkömmliche <code>textProperty()</code> mit unseren Properties.
Diese können wie gefolgt aussehen.
Beispiel: 
<code>      businessControl.firstYearProperty().bindBidirectional(model.getWindparkProxy().production2015MwhProperty()); </code> <br>
    <code>  businessControl.secondYearProperty().bind(model.getWindparkProxy().production2016MwhProperty());</code>
   <code>     businessControl.thirdYearProperty().bind(model.getWindparkProxy().production2017MwhProperty());</code>
     <code>   businessControl.fourtfhYearProperty().bind(model.getWindparkProxy().production2018MwhProperty()); </code>
 
 Unsere Properties stehen für die 4 detallierte Jahre. <br>
 **Produktionsjahr 2015 -> firstYearProperty() <br>
 Produktionsjahr 2016 -> secondYearProperty()<br>
 Produktionsjahr 2017 -> thirdYearProperty()<br>
 Produktionsjahr 2018 -> fourtYearProperty()** <br>
 
 **Slider Control**
1. Fügen Sie zunächste eine Deklaration <code>private SliderControl sliderControl;</code> ein.
2. Im berreich <code>private void initializeParts()</code> initialisieren Sie den Converter mit <code> sliderControl = new SliderControl(); </code>
3. Nun müssen wir die Bindings anpassen. Ersetzen Sie die herkömmliche <code>textProperty()</code> mit unseren Properties.
Diese können wie gefolgt aussehen.
Beispiel: 
<code>sliderControl.valueProperty().bindBidirectional(model.getWindparkProxy().production2015MwhProperty());
); </code> <br>
    <code>  sliderControl.valueProperty().bindBidirectional(model.getWindparkProxy().production2016MwhProperty());</code>
   <code>     sliderControl.valueProperty().bindBidirectional(model.getWindparkProxy().production2017MwhProperty());</code>
     <code>   sliderControl.valueProperty().bindBidirectional(model.getWindparkProxy().production2018MwhProperty()); </code>
 
 Es gibt nur eine Propertie die für alle Jahre gilt: <code>valueProperty()</code>
 **WICHTIG!**
 entfernen Sie alle <code> new NumberStrinConverter </code>, dieser Fall tritt nicht auf. (wegen unserer valueProperty()) 
 
 
 
 
