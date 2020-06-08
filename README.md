# consumption-converter
![Screenshot](logo_converter.jpeg) <br>
Custom Control for WindparksFX
Dieser Custom Control bietet eine klare Übersicht, welche Geräte im Haushalt am meisten Strom im Jahr verbrauchen. Unser Converter nimmt den Jahresschnitt von den Geräten und teilt diese durch die erzeugte Enerige von den Windparks.

# Gebrauch

**Package**

Zum zunächst unser Custom Control zu implementieren müssen Sie das consumption-converter Projekt herunterladen. Kopieren Sie den gesamten <code>consumption_converter</code> Ordner und den Ordner mit den Ressourcen.

**UI Converter Dashboard**<br>

![Screenshot](converter_info.png) <br>

Um dieses Custom Control zu brauchen, benötigen Sie die Klasse <code>ConverterControl.java</code><br>

**Business control slider**<br>

![Screenshot](slider_info.png) <br>

Um dieses Custom Business Control zu brauchen, benötigen Sie die Klasse <code>SliderControl.java</code><br>

# Implementieren

Wählen Sie zunächst welches Custom Control Sie implementieren möchten. (Converter oder Slider)
Danach begeben Sie sich in Ihre gewünschte Klasse wo Sie das UI platzieren möchten.

**Converter Control**
1. Fügen Sie zunächst folgende Deklarationen
<code>private ConverterControl convertercontrol;</code> ein.<br>

2. Im Bereich <code>private void initializeParts()</code> initialisieren Sie den Converter mit <br>
<code> convertercontrol = new ConverterControl(); </code><br> 

3. Nun müssen wir die Bindings anpassen. Ersetzen Sie die herkömmliche <code>textProperty()</code> mit unseren Properties.
Diese können wie gefolgt aussehen.
Beispiel: 
<code>      convertercontrol.firstYearProperty().bindBidirectional(model.getWindparkProxy().production2015MwhProperty()); </code> <br>
    <code>  convertercontrol.secondYearProperty().bindBidirectional(model.getWindparkProxy().production2016MwhProperty()); </code>
   <code>     convertercontrol.thirdYearProperty().bindBidirectional(model.getWindparkProxy().production2017MwhProperty()); </code>
     <code>   convertercontrol.fourtfhYearProperty().bindBidirectional(model.getWindparkProxy().production2018MwhProperty()); </code>
 
 Unsere Properties stehen für die 4 detailliertenw Jahre. <br>
 **Produktionsjahr 2015 -> firstYearProperty() <br>
 Produktionsjahr 2016 -> secondYearProperty()<br>
 Produktionsjahr 2017 -> thirdYearProperty()<br>
 Produktionsjahr 2018 -> fourtYearProperty()** <br>
 
 **Slider Control**
1. Fügen Sie zunächst folgende vier Deklaration <br>
<code>private SliderControl sliderControl15;</code> <br>
<code>private SliderControl sliderControl16;</code> <br>
<code>private SliderControl sliderControl17;</code> <br>
<code>private SliderControl sliderControl18;</code>ein. <br>

2. Im Bereich <code>private void initializeParts()</code> initialisieren Sie die Converter mit <br>
<code> sliderControl15 = new SliderControl(); </code><br>
<code> sliderControl16 = new SliderControl(); </code><br>
<code> sliderControl17 = new SliderControl(); </code><br>
<code> sliderControl18 = new SliderControl(); </code><br>

3. Nun müssen wir die Bindings anpassen. Ersetzen Sie die herkömmliche <code>textProperty()</code> mit unseren Properties.
Diese können wie gefolgt aussehen.
Beispiel: 
<code>sliderControl15.valueProperty().bindBidirectional(model.getWindparkProxy().production2015MwhProperty()); </code> <br>
    <code>  sliderControl16.valueProperty().bindBidirectional(model.getWindparkProxy().production2016MwhProperty());</code>
   <code>     sliderControl17.valueProperty().bindBidirectional(model.getWindparkProxy().production2017MwhProperty());</code>
     <code>   sliderControl18.valueProperty().bindBidirectional(model.getWindparkProxy().production2018MwhProperty()); </code>
 
 Es gibt nur eine Property die für alle Jahre gilt: <code>valueProperty()</code>
 **WICHTIG!**
 entfernen Sie alle <code> new NumberStrinConverter </code>, dieser Fall tritt nicht auf. (wegen unserer valueProperty()) 
 
 
 
 
