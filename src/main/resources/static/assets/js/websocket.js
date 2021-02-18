// Web Socket
var stompClient = null;
var connectId = null;
var eventFuntion = null;

class WebSocket{
    
    connect(id, fn) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
    
        eventFuntion = fn;
        connectId = id;

        stompClient.connect({}, this.onConnected, this.onError);
    }
    
    onConnected() {
        // Subscribe to the Public Topic
        stompClient.subscribe('/sub/' + connectId , this.onMessageReceived);
    }
    
    onError(error) {

    }
    
    sendMessage() {
        var param = {
            msg: "123"
        };

        if (stompClient) {
            stompClient.send("/send/" + connectId, {}, JSON.stringify(param));
        }
    }
    
    onMessageReceived(data) {
        console.log(data);
        eventFuntion(data);
    }
}


