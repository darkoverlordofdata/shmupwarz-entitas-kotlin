package com.darkoverlordofdata.shmupwarz.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.shmupwarz.*

class CollisionSystem()
      : IExecuteSystem,
        ISetPool {

    private lateinit var pool: Pool
    private lateinit var bullets: Group
    private lateinit var enemies: Group
    private lateinit var players: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        bullets = pool.getGroup(Matcher.Bullet)
        enemies = pool.getGroup(Matcher.Enemy)
        players = pool.getGroup(Matcher.Player)
    }

    override fun execute() {
        for (bullet in bullets.entities) {
            for (enemy in enemies.entities) {
                if (collidesWith(bullet, enemy))
                        collisionHandler(bullet, enemy)
            }
        }
    }

    fun collidesWith(e1: Entity, e2:Entity):Boolean {
        val position1 = e1.position
        var position2 = e2.position
        val a = (position1.x - position2.x).toDouble()
        val b = (position1.y - position2.y).toDouble()

        return ((Math.sqrt(a * a + b * b)) - e1.bounds.radius) < e2.bounds.radius
    }

    fun collisionHandler(weapon:Entity, ship:Entity) {
        val pos = weapon.position
        pool.createSmallExplosion(pos.x, pos.y)
        weapon.setDestroy(true)
        var health = ship.health
        health.currentHealth -=1
        if (health.currentHealth <= 0f) {
            val position = ship.position
            pool.createBigExplosion(position.x, position.y)
            ship.setDestroy(true)
            val player = players.singleEntity
            if (player != null)
                player.score.value += health.maximumHealth.toInt()
        }
    }
}