package com.makotomiyamoto.hgspecials.listeners.consumeable;

import com.makotomiyamoto.hgspecials.meta.Eval;
import com.makotomiyamoto.hgspecials.meta.Particles;
import com.makotomiyamoto.hgspecials.meta.Specials;
import com.makotomiyamoto.hgspecials.meta.Template;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Spinach implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!Eval.shouldPassActionScript(event)
                || Eval.mightThrowAStupidNullError(event)
                || !Eval.match(event.getItem(), Specials.CAN_OF_SPINACH)) {
            return;
        }
        Player player = event.getPlayer();
        if (player.hasPotionEffect(PotionEffectType.HEALTH_BOOST)) {
            player.sendMessage(ChatColor.RED + "You are already under this effect!");
            return;
        }
        player.sendMessage(Template.PREFIX
                + ChatColor.GREEN + "+4 hearts "
                + ChatColor.GRAY + "for "
                + ChatColor.AQUA + "1:00"
                + ChatColor.GRAY + "!");
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1200, 0, true, false));
        player.setHealth(player.getHealth()+4);
        player.getInventory().getItemInMainHand().setAmount(
                player.getInventory().getItemInMainHand().getAmount() - 1);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 0.4f, 1.5f);
        Particles.surroundHearts(player);
    }
}
