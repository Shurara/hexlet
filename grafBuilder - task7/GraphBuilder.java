import java.util.*;
import java.util.concurrent.*;

public class GraphBuilder implements Callable<Set<GoField>> {

    private final ExecutorService executorService;

    private final Figure nextFigure;

    private final GoField currentField;

    private final int deepLevel;

    public GraphBuilder(
            final ExecutorService executorService,
            final Figure currentFigure,
            final GoField currentField,
            final int deepLevel) {
        this.executorService = executorService;
        this.currentField = currentField;
        this.nextFigure = currentFigure == Figure.WHITE ? Figure.BLACK : Figure.WHITE;
        this.deepLevel = deepLevel;
    }

    @Override
    public Set<GoField> call() throws Exception {
        // BEGIN (write your solution here) #1
        if (isCurrentFieldFinal()) {
            /*HashSet<GoField> tempSet = new HashSet<>();
            tempSet.add(currentField);
            return tempSet;*/
            return new HashSet<GoField>(){{add(currentField);}};
        }
        // END #1
        // BEGIN (write your solution here) #2
        final List<Future<Set<GoField>>> futures = new ArrayList<>();
        final Set<GoField> result = new HashSet<>();
        // END #2
        // BEGIN (write your solution here) #3
        for (int y = 0; y < GoField.FIELD_SIZE; y++) {
            for (int x = 0; x < GoField.FIELD_SIZE; x++) {
                if (currentField.figures[y][x] != null) {
                    continue;
                }
                /*
                //These lines are needed if you do not have a custom field constructor!
                final GoField newField = new GoField();
                for (int i = 0; i < GoField.FIELD_SIZE; i++) {
                    for (int j = 0; j < GoField.FIELD_SIZE; j++) {
                        newField.figures[i][j] = currentField.figures[i][j];
                    }//тут важно скопировать содержимое подмасивов, а не ссылки на подмассивы!
                }
                newField.figures[y][x] = nextFigure;*/

                final GraphBuilder graphBuilder
                        = new GraphBuilder(
                        executorService,
                        nextFigure,
                        new GoField(currentField, nextFigure, y, x),
                        deepLevel + 1);
                if (isAsync()) {
                    final Future<Set<GoField>> future
                            = executorService.submit(graphBuilder);
                    futures.add(future);
                } else {
                    result.addAll(graphBuilder.call());
                }
            }
        }
        // END #3
        // BEGIN (write your solution here) #4
        if (!futures.isEmpty()) {
            for (Future<Set<GoField>> future : futures) {
                try {
                    result.addAll(future.get());
                } catch (final InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
        // END #4
    }

    private boolean isAsync() {
        return deepLevel <= 2;
    }

    private boolean isCurrentFieldFinal() {
        for (Figure[] line : currentField.figures) {
            for (Figure f : line) {
                if (f == null) {
                    return false;
                }
            }
        }
        return true;
    }
}