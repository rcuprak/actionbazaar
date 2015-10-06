/**
 *  EJB 3 in Action
 *  Book: http://manning.com/panda2/
 *  Code: http://code.google.com/p/action-bazaar/
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * Constructs a new chat instance.
 */
function Bulletin() {

    var COMMAND_TYPE = {
            CONNECT:"CONNECT",
            MESSAGE:"MESSAGE",
            CSR_LOST:"CSR_LOST",
            CLIENT_LOST:"CLIENT_LOST"
    };

    /**
     * Callback for successful connection open.
     */
    function onOpen() {
        $("#messageWindow").append(welcomeMsg + "<br/>");
    };


    /**
     * Callback when the connection closes
     */
    function onClose() {
        if (!failed) {
            $("#messageWindow").append("Chat Session Over.<br/>");
            $('#btnSend').attr("disabled", true);
            $('#btnClose').attr("disabled", true);
            $('#messageWindow').attr("disabled", true);
        }
    };

    /**
     * Invoked when a message arrives.
     * @param evt - event
     */
    function onMessage(evt) {
        var msg = eval('(' + evt.data + ')');
        if (msg.type === COMMAND_TYPE.MESSAGE) {
            $("#messageWindow").append('<span style="font-weight: bold; padding-right: 10px;">' + msg.user + '</span>');
            $("#messageWindow").append(msg.message + '<br/>');
        } else if (msg.type === COMMAND_TYPE.CONNECT) {
            setButtonDisabledState(false);
            $("#messageWindow").append('<div style="font-weight: bold;">' + msg.message + '</div>');
        } else if (msg.type === COMMAND_TYPE.CSR_LOST) {
            setButtonDisabledState(true);
            $("#messageWindow").append('<div style="font-weight: bold;">Waiting for another CSR...</div>');
        } else if (msg.type === COMMAND_TYPE.CLIENT_LOST) {
            setButtonDisabledState(true);
            $("#messageWindow").append('<div style="font-weight: bold;">Client has disconnected...</div>');
        } else {
            $("#messageWindow").append('<span style="font-weight: bold;">UNKNOWN MESSAGE ' + msg.type + '</span>');
        }
    };
    
    function setButtonDisabledState(disabled) {
        $('#btnSend').attr("disabled",disabled);
        $('#btnClose').attr("disabled",disabled);
        $('#messageWindow').attr("disabled",disabled);
    }
    

    function onError(evt) {
        console.log("web socket error!!" + evt);
        that.failed = true;
        $("#messageWindow").append("Connection lost.<br/>");
    };

    this.postMessage = function() {
        var message = {
            "type" : COMMAND_TYPE.MESSAGE,
            "message": $('#message').val()};
        webSocket.send(JSON.stringify(message));
        $('#message').val("");
    };

    /**
     * Closes the chat session
     */
    this.closeWebSocket = function() {
        webSocket.close();
    };
    
    /**
     * Sends a command to the server
     * @param cmd - command to be sent to the server
     */
    this.sendCommand = function (cmd) {
        webSocket.send(JSON.stringify(cmd));
    };

    try {
        $('#btnSend').attr("disabled", true);
        $('#btnClose').attr("disabled", true);
        $('#messageWindow').attr("disabled", true);
        var wsUri = "ws://" + document.location.host + '/chapter14/admin/bulletin';
        var webSocket = new WebSocket(wsUri);
        webSocket.onopen = onOpen;
        webSocket.onclose =onClose;
        webSocket.onmessage = onMessage;
        webSocket.onerror = onError;
        var failed = false;
    } catch (err) {
        $("#messageWindow").append("Error creating connection: " + err);
        console.log("web socket error: " + err);
    }
}


