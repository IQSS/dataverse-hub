import requests
import pandas
from ipyleaflet import Map, Marker, Icon

from shiny import render, ui
from shiny.express import input
from shinywidgets import render_widget  

ui.panel_title("Dataverse Hub")

url = "http://localhost:8080/api/installation/status"
response = requests.get(url)
df = pandas.json_normalize(response.json())

@render.data_frame
def txt():        
    return render.DataTable(df.drop(columns=['recordId', 'captureDate', 'build']))

@render_widget  
def map():
    m = Map(center=(50.6252978589571, 0.34580993652344), zoom=3)
    marker1 = Marker(location=(51.5, -0.09), icon=Icon(color='blue'))
    marker2 = Marker(location=(51.51, -0.1), icon=Icon(color='green'))
    marker3 = Marker(location=(51.49, -0.08), icon=Icon(color='red'))
    m.add_layer(marker1)
    m.add_layer(marker2)
    m.add_layer(marker3)
    return m