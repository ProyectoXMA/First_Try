import com.mygdx.game.model.obstacles.Duck;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.obstacles.ObstacleVisitor;
import com.mygdx.game.model.obstacles.Stone;

class ObstacleVisitorTest implements ObstacleVisitor {
    boolean visitedLog = false;
    boolean visitedStone = false;
    boolean visitedDuck = false;

    @Override
    public void visitLog(Log log) {
        visitedLog = true;
    }

    @Override
    public void visitStone(Stone stone) {
        visitedStone = true;
    }

    @Override
    public void visitDuck(Duck duck) {
        visitedDuck = true;
    }
}
