package Main;

public class Rules {
    CellManager cMan;
    Rules(CellManager cMan)
    {
        this.cMan = cMan;
    }
    
    public void overcrowding()
    {
        cMan.cellBuffer.stream().filter((c) -> (cMan.checkNeighbours(c) > 3)).forEachOrdered((c) -> {
            c.setState(false);
        });
    }
    
    public void underpopulation()
    {
        cMan.cellBuffer.stream().filter((c) -> (cMan.checkNeighbours(c) < 2)).forEachOrdered((c) -> {
            c.setState(false);
        });
    }
    
    public void creation()
    {
        cMan.deadCells.clear();
        cMan.cellBuffer.forEach((c) -> {
            cMan.checkNeighbours(c);
        });
        
        cMan.deadCells.forEach((c) -> {
            if (cMan.checkLive(c.getX(),c.getY())) 
            {return;}
            int count = 0;
            count = cMan.deadCells.stream().filter((ce) -> (c.equals(ce))).map((item) -> 1).reduce(count, Integer::sum);
            if (count == 3) {
                cMan.createCell(c.getX(), c.getY(), true);
            }
        });
    }
}
