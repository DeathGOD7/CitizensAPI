package net.citizensnpcs.api.attachment.builtin;

import net.citizensnpcs.api.abstraction.CommandSender;
import net.citizensnpcs.api.abstraction.Player;
import net.citizensnpcs.api.attachment.Attachment;
import net.citizensnpcs.api.exception.NPCLoadException;
import net.citizensnpcs.api.util.DataKey;

/**
 * Represents the owner of an NPC.
 */
public class Owner extends Attachment {
    private static final String SERVER = "server";
    private String owner = SERVER;

    /**
     * Gets the owner of an NPC.
     * 
     * @return Name of the owner of an NPC
     */
    public String getOwner() {
        return owner;
    }

    public boolean isOwnedBy(String name) {
        return owner.equalsIgnoreCase(name);
    }

    /**
     * Gets if the given player is the owner of an NPC.
     * 
     * @param player
     *            Player to check
     * @return Whether the given player is the owner of an NPC
     */
    public boolean isOwnedBy(CommandSender sender) {
        if (sender instanceof Player) {
            return owner.equalsIgnoreCase(sender.getName()) || sender.hasPermission("citizens.admin")
                    || (owner.equals(SERVER) && sender.hasPermission("citizens.admin"));
        }
        return owner.equals(SERVER);
    }

    @Override
    public void load(DataKey key) throws NPCLoadException {
        try {
            owner = key.getString("");
        } catch (Exception ex) {
            owner = SERVER;
            throw new NPCLoadException("Invalid owner.");
        }
    }

    @Override
    public void save(DataKey key) {
        key.setString("", owner);
    }

    /**
     * Sets the owner of an NPC.
     * 
     * @param owner
     *            Name of the player to set as owner of an NPC
     */
    public void setOwner(String owner) {
        this.owner = owner.toLowerCase();
    }

    @Override
    public String toString() {
        return "Owner{" + owner + "}";
    }
}