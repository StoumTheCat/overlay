package com.stoum.overlay.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.stoum.overlay.entity.enums.GameType
import com.stoum.overlay.entity.overlay.GamePlayer
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OrderBy
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "game")
class Game (
        @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
        @Enumerated(EnumType.STRING)
        var type: GameType,
        var tournamentId: Int? = null,
        var gameNum: Int? = null,
        var tableNum: Int? = null,
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "game_id")
        @OrderBy("place ASC")
    var players: MutableList<GamePlayer> = mutableListOf(),
    var startred: Boolean? = null,
    var visibleOverlay: Boolean? = true,
    var visibleRoles: Boolean? = true
) {

    @Transient
    @JsonInclude
    var playersOrdered = listOf<String>()
}
