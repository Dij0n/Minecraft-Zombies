package dijon.zombiesbase.shooting.recoil;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedAttribute;
import com.comphenix.protocol.wrappers.WrappedAttributeModifier;
import dijon.zombiesbase.utility.PluginGrabber;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;


public class Recoiler {

    PacketContainer zoomInPacket;
    PacketContainer zoomOutPacket;
    Player p;

    public Recoiler(Player p){
        this.p = p;
        zoomOutPacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.UPDATE_ATTRIBUTES);
        zoomOutPacket.getIntegers().write(0, p.getEntityId());

        WrappedAttribute speedAttribute = WrappedAttribute.newBuilder()
                .attributeKey("generic.movement_speed")
                .baseValue(0.13)
                .build();

        zoomOutPacket.getAttributeCollectionModifier().write(0, Collections.singletonList(speedAttribute));

        zoomInPacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.UPDATE_ATTRIBUTES);
        zoomInPacket.getIntegers().write(0, p.getEntityId());

        WrappedAttribute normalAttribute = WrappedAttribute.newBuilder()
                .attributeKey("generic.movement_speed")
                .baseValue(0.1)
                .build();

        zoomInPacket.getAttributeCollectionModifier().write(0, Collections.singletonList(normalAttribute));
    }

    public void zoomOut(){
        ProtocolLibrary.getProtocolManager().sendServerPacket(p, zoomOutPacket);
    }

    public void zoomIn(){
        ProtocolLibrary.getProtocolManager().sendServerPacket(p, zoomInPacket);
    }

}
