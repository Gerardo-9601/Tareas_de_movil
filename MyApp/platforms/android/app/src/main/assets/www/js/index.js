/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
         document.getElementById('btntomarfoto').addEventListener("click",takephoto);
         document.getElementById('btnestado').addEventListener("click",ShowBatteryStatus);
         document.getElementById('wifi').addEventListener("click",checkConnection);
         document.getElementById('btndevice').addEventListener("click",version);



    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
    }



};

app.initialize();

         function takephoto(){
        navigator.camera.getPicture(onSuccess, onFail, { quality: 25,
            destinationType: Camera.DestinationType.DATA_URL
        });

        }

        function onSuccess(imageData) {
            var image = document.getElementById('img');
            image.src = "data:image/jpeg;base64," + imageData;
        }

        function onFail(message) {
            alert('Failed because: ' + message);
        }

        function vibrar() {
           navigator.vibrate(1000);
        }


        function ShowBatteryStatus(){
        window.addEventListener("batterystatus", onBatteryStatus, false);
        }

      function onBatteryStatus(status) {
         alert("Nivel Bateria: " + status.level + " Cargando:" + status.isPlugged);
      }

      function version(){
      alert("UUID:"+device.uuid +"\n"+"Plataforma:"+device.platform +"\n"+"Version SO:"+device.version+"\n"+"Modelo:"+device.model+"\n"+"Fabricante:"+device.manufacturer);
      }



    function checkConnection() {
        var networkState = navigator.connection.type;

        var states = {};
        states[Connection.UNKNOWN]  = 'Unknown connection';
        states[Connection.ETHERNET] = 'Ethernet connection';
        states[Connection.WIFI]     = 'WiFi connection';
        states[Connection.CELL_2G]  = 'Cell 2G connection';
        states[Connection.CELL_3G]  = 'Cell 3G connection';
        states[Connection.CELL_4G]  = 'Cell 4G connection';
        states[Connection.CELL]     = 'Cell generic connection';
        states[Connection.NONE]     = 'No network connection';

        alert('Tipo de Conexion: ' + states[networkState]);
    }