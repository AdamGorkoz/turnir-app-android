package turka.turnirapp.mapper;

import com.models.PlayerModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import turka.turnirapp.di.di.PerActivity;
import turka.turnirapp.model.Player;

/**
 * Created by turka on 5/6/2017.
 */

@PerActivity
public class TeamPlayerMapper {
    @Inject
    public TeamPlayerMapper() {}


    public Player transform(PlayerModel playerModel) {
        if (playerModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new Player(playerModel.getID(),
                playerModel.getGoals(),
                playerModel.getName(),
                playerModel.getNumber(),
                playerModel.getRecordStatus(),
                playerModel.getPrice(),
                playerModel.getTeamID(),
                playerModel.getTeamName());
    }

    public Collection<Player> transform(Collection<PlayerModel> playerModelCollection) {
        Collection<Player> playerCollection;

        if (playerModelCollection != null && !playerModelCollection.isEmpty()) {
            playerCollection = new ArrayList<>();
            for (PlayerModel playerModel : playerModelCollection) {
                playerCollection.add(transform(playerModel));
            }
        } else {
            playerCollection = Collections.emptyList();
        }

        return playerCollection;
    }
}
