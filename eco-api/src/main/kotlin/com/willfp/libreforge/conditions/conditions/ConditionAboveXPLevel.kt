package com.willfp.libreforge.conditions.conditions

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.conditions.Condition
import com.willfp.libreforge.updateEffects
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerExpChangeEvent


class ConditionAboveXPLevel : Condition("above_xp_level") {
    @EventHandler(
        priority = EventPriority.MONITOR,
        ignoreCancelled = true
    )
    fun handle(event: PlayerExpChangeEvent) {
        val player = event.player

        player.updateEffects()
    }

    override fun isConditionMet(player: Player, config: Config): Boolean {
        return player.level >= config.getInt("level")
    }

    override fun validateConfig(config: Config): List<com.willfp.libreforge.ConfigViolation> {
        val violations = mutableListOf<com.willfp.libreforge.ConfigViolation>()

        config.getIntOrNull("level")
            ?: violations.add(
                com.willfp.libreforge.ConfigViolation(
                    "level",
                    "You must specify the xp level!"
                )
            )

        return violations
    }
}