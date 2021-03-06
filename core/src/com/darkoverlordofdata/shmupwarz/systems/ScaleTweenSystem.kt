package com.darkoverlordofdata.shmupwarz.systems

/**
 * ScaleTweenSystem
 *
 * Tween the scale - used for explosions
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.shmupwarz.*

class ScaleTweenSystem()
      : IExecuteSystem,
        ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Scale, Matcher.Tween))
    }

    override fun execute() {
        val delta = Gdx.graphics.deltaTime

        for (entity in group.entities) {
            val tween = entity.tween
            val scale = entity.scale
            var x = scale.x
            var y = scale.y
            var active = tween.active

            x += (tween.speed * delta)
            y += (tween.speed * delta)
            if (x > tween.max) {
                x = tween.max
                y = tween.max
                active = false
            } else if (x < tween.min) {
                x = tween.min
                y = tween.min
                active = false
            }
            scale.x = x
            scale.y = y
            tween.active = active

        }
    }
}