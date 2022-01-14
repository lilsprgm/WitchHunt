package Game_operator;

/**
 * Enumeration des etapes d'un tour de Jeu complet.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public enum UpdateCode {
    ATTENTE,
    INIT_NUMBER_PLAYER,
    INIT_NUMBER_BOT,
    ERROR_NUMBER,
    INIT_NAME_PLAYER,
    INIT_DIFFICULTY_BOT,
    ERROR_DIFFICULTY,

    GAME_INIT_ROUND,
    GAME_ROUND,
    GAME_END_ROUND,

    CHOOSE_IDENTITY,
    END_CHOOSE_IDENTITY,


    ACCUSE_OR_PLAY,
    ACCUSE,
    END_ACCUSATION,
    IS_ACCUSED,
    IS_REVEALED,

    PLAY_CARD_HUNT,
    PLAY_CARD_WITCH,
    EFFECT_CARD_HUNT,
    EFFECT_CARD_WITCH,
    END_CHOOSE_CARD,
    EMPTY_DECK,

    END_PLAY,

    BOT_ACCUSE,
    BOT_PLAY_HUNT,
    BOT_IS_ACCUSED,
    BOT_REVEAL,
    BOT_PLAY_WITCH,

    CARD_CHOOSE_PLAYER,


    END_GAME,

}
