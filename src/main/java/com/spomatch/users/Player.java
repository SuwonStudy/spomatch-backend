package com.spomatch.users;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.spomatch.players.PlayerId;

/**
 * User에서 Player 개념이 필요할 때 사용하는 밸류입니다.
 * 
 * @author Seongbin Kim
 */
@Embeddable
public class Player {

    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "player_id"))
    )
    private PlayerId id;
    
    
}
