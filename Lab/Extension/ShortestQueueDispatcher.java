public class ShortestQueueDispatcher extends JobDispatcher {

    public ShortestQueueDispatcher(int k, boolean showvis){
        super(k,showvis);
    }
    @Override
    public Server pickServer(Job j) {
        Server minLoad= serversList.get(0);
        
        for(Server server:serversList){
            if(server.size()<minLoad.size()){
                minLoad = server;
                
            }
            
        } 
        return minLoad;

    }
    
}
