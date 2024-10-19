import requests
import pandas
from shiny.express import input, ui, render
from shinywidgets import render_widget  
from ipyleaflet import Map, Marker, AwesomeIcon

with ui.sidebar():
    ui.input_checkbox("show_ok", "Show OK", True)
    ui.input_checkbox("show_unreachable", "Show Unreachable", True)
    ui.input_checkbox("show_unrecognizable", "Show Unrecognizable", True)

ui.page_opts(title="Dataverse Hub")
ui.page_opts(fillable=True)

# ui.panel_title("DV Installations")

status_url = "http://localhost:8080/api/installation/status"
status_response = requests.get(status_url)
status_data_frame = pandas.json_normalize(status_response.json())

installations_url = "http://localhost:8080/api/installation"
installations_response = requests.get(installations_url)
installations_data_frame = pandas.json_normalize(installations_response.json())

with ui.layout_columns(fill=True):
    with ui.card(full_screen=True):
        ui.card_header("Installations data")
        @render.data_frame
        def status_data_chart():        
            return render.DataTable(status_data_frame.drop(columns=['recordId', 'captureDate', 'build']), width='100%', height='100%', filters=True)
    
    with ui.card(full_screen=True):
        ui.card_header("Interactive Map")
        @render_widget  
        def map():
            m = Map(center=(50.6252978589571, 0.34580993652344), zoom=2)
            for idx, row in installations_data_frame.iterrows():
                status = status_data_frame.loc[status_data_frame['dvHubId'] == row['dvHubId'], 'status'].values[0]
                if status == 'OK':
                    marker_color = 'lightgreen'
                    icon_name = 'check'
                    icon_color = 'lightgreen'
                elif status == 'UNREACHABLE':
                    marker_color = 'lightred'
                    icon_name = 'power-off'
                    icon_color = 'white'
                elif status == 'UNRECOGNIZABLE':
                    marker_color = 'orange'
                    icon_name = 'warning'
                    icon_color = 'yellow'
                else:
                    color = 'blue'  # Default color if status is unknown
                marker = Marker(location=(row['latitude'], row['longitude']), icon=AwesomeIcon(name=icon_name, marker_color=marker_color, icon_color=icon_color))                
                m.add_layer(marker)
            return m

    
