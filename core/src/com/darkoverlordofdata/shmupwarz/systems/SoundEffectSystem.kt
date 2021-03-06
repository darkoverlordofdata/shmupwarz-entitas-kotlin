package com.darkoverlordofdata.shmupwarz.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.shmupwarz.*

class SoundEffectSystem()
      : IExecuteSystem,
        ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    val pew: Sound by lazy {
        Gdx.audio.newSound(Gdx.files.internal("sfx/pew.ogg"))
    }
    val asplode: Sound by lazy {
        Gdx.audio.newSound(Gdx.files.internal("sfx/asplode.ogg"))
    }
    val smallasplode: Sound by lazy {
        Gdx.audio.newSound(Gdx.files.internal("sfx/smallasplode.ogg"))
    }

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.SoundEffect))
    }

    override fun execute() {
        for (entity in group.entities) {
            when (entity.soundEffect.effect) {
                Effect.PEW -> pew
                Effect.ASPLODE -> smallasplode
                Effect.SMALLASPLODE -> asplode
                else -> null
            }?.play()
            entity.removeSoundEffect()
        }
    }

}