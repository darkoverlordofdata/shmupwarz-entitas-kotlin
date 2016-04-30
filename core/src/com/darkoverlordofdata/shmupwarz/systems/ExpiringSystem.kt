package com.darkoverlordofdata.shmupwarz.systems

/**
 * EExpiringSystem
 *
 * Terminate entities after specified time count
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.shmupwarz.*

class ExpiringSystem()
      : IExecuteSystem,
        ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Expires))
    }

    override fun execute() {
        val delta = Gdx.graphics.deltaTime
        for (entity in group.entities) {
            val value = entity.expires.delay - delta
            entity.expires.delay = value
            if (value < 0) {
                pool.destroyEntity(entity)
            }
        }
    }

}