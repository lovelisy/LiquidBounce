/*
 * LiquidBounce OneConfig integration.
 *
 * Registers a OneConfig settings page that mirrors every LiquidBounce module
 * as a toggle switch, so all modules can be enabled/disabled directly from
 * OneConfig's sidebar. The LiquidBounce module system, event bus and mixins
 * are fully preserved — OneConfig is used purely as the settings GUI.
 */
package net.ccbluex.liquidbounce.integration.oneconfig

import org.polyfrost.oneconfig.api.config.v1.ConfigManager

object OneConfigIntegration {

    /**
     * Called once during client initialization, after modules are registered.
     * Submits the generated config (one switch per module) to OneConfig, which
     * then shows it as a "LiquidBounce Modules" card in its sidebar.
     */
    fun initialize() {
        runCatching {
            ConfigManager.submitForInitialization(LiquidBounceModulesConfig)
        }.onFailure {
            // OneConfig is optional at runtime; never let a GUI issue crash the client.
            System.err.println("[LiquidBounce] Failed to register OneConfig page: ${it.message}")
        }
    }
}
