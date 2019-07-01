package ds_algo_study.graph.disjointSet;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import ds_algo_study.graph.DisjointSet;


public class BridgeRecoverer {
    private final DisjointSet ds;
    
    public BridgeRecoverer(int numOfBridges) {
        ds = new DisjointSet(numOfBridges);
    }
    
    public static boolean isFullyRecovered(BridgeRecoverer recoverer, Bridge... bridges) {
        checkNotNull(recoverer);
        checkArgument(checkNotNull(bridges).length > 0);
        Bridge first = bridges[0];
        for (int i = 1; i < bridges.length; i++) {
            if (!recoverer.isConnected(first, bridges[i])) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isConnected(Bridge b, Bridge other) {
        checkNotNull(b);
        checkNotNull(other);
        return ds.isConnected(b.from, other.from);
    }
    
    public BridgeRecoverer recover(Bridge b) {
        checkNotNull(b);
        ds.merge(b.from, b.to);
        return this;
    }
    
    private class Bridge {
        private final String name;
        private final int from;
        private final int to;
        
        Bridge(String name, int from, int to) {
            this.name = name;
            this.from = from;
            this.to = to;
        }
    }
}
