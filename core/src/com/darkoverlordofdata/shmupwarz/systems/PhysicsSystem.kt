package com.darkoverlordofdata.shmupwarz.systems

/**
 * PhysicsSystem
 *
 * Simplest possible motion physics
 * Calculates new position based on velocity
 *
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.shmupwarz.*

class PhysicsSystem()
      : IExecuteSystem,
        ISetPool {

    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        group = pool.getGroup(Matcher.allOf(Matcher.Position, Matcher.Velocity))
    }

    override fun execute() {
        val delta = Gdx.graphics.deltaTime
        for (entity in group.entities) {
            entity.position.x += (entity.velocity.x * delta)
            entity.position.y -= (entity.velocity.y * delta)
        }
    }
}