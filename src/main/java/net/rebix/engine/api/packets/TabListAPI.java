package net.rebix.engine.api.packets;

import org.bukkit.entity.Player;

public class TabListAPI {

    private final Player player;
    private String header = "";
    private String footer = "";

    public TabListAPI(Player player){
        this.player = player;
    }
    public TabListAPI(Player player,String header,String footer){
        this.player = player;
        this.header = header;
        this.footer = footer;
    }


    public Player getPlayer() {
        return player;
    }

    public void send(){
        player.setPlayerListHeader(header);
        player.setPlayerListFooter(footer);
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getHeader(){
        return header;
    }

    public String getFooter(){
        return footer;
    }
}
