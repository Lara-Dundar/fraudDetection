import java.util.ArrayList;
import java.util.List;

public class dbscan {

    private double epsilon;
    private int minPts;

    public dbscan(double epsilon, int minPts) {
        this.epsilon = epsilon;
        this.minPts = minPts;
    }

    public static double getEpsilon(List<List<String>> parameters) {
        List<String> epsilon_minPts = parameters.get(1);
        return Double.parseDouble((epsilon_minPts.get(0)));
    }

    public static int get_minPts(List<List<String>> parameters) {
        List<String> epsilon_minPts = parameters.get(1);
        return Integer.parseInt(epsilon_minPts.get(1));
    }

    public List<point> createDataPoints(List<List<String>> data) {
        List<point> points = new ArrayList<>();
        for(int i=1 ; i<data.size() ; i++) {
            List<String> each_data = data.get(i);
            List<Double> each_point = new ArrayList<>();

            for (String eachString : each_data) {
                each_point.add(Double.parseDouble(eachString));
            }
            point dataPoint = new point(each_point);
            points.add(dataPoint);
        }
        return points;
    }

    public double getEuclideanDistance(List<Double> p1, List<Double> p2) {
        double sum = 0.0;
        for (int i=0; i<p1.size() ; i++) {
            sum += Math.pow(p1.get(i)-p2.get(i), 2);
        }
        return Math.sqrt(sum);
    }


    public boolean checkIfCore(List<point> points, point dataPoint) {
        int counter = 0;
        for(int i=0 ; i<points.size() ; i++) {
            double distance_Between_Points = 0.0;
            distance_Between_Points = getEuclideanDistance(dataPoint.values, points.get(i).values);
            if(distance_Between_Points <= epsilon) {
                counter++;
                if(counter >= minPts) { // Counter will count the point itself as a neighbour too.
                    return true;
                }
            }
        }
        return false;
    }

    public List<corePoint> createCorePoints(List<point> points) {
        List<corePoint> corePoints = new ArrayList<>();
        for(int i=0 ; i<points.size() ; i++) {
            boolean isCore = checkIfCore(points, points.get(i));
            if(isCore) {
                corePoint cp = new corePoint(points.get(i).values);
                corePoints.add(cp);
            }
        }
        return corePoints;
    }
    
    public List<noncorePoint> createNonCorePoints(List<point> points) {
        List<noncorePoint> nonCorePoints = new ArrayList<>();
        for(int i=0 ; i<points.size() ; i++) {
            boolean isCore = checkIfCore(points, points.get(i));
            if(!isCore) {
                noncorePoint ncp = new noncorePoint(points.get(i).values);
                nonCorePoints.add(ncp);
            }
        }
        return nonCorePoints;
    }

    public List<cluster> createClusters(List<point> points, List<corePoint> corePointsList) {
        List<cluster> allClusters = new ArrayList<>();

        for (point p : points) {
            if (!p.isVisited) {
                //System.out.println("Calling expandCluster on point: " + p.values); // Debug statement
                List<point> clusterPoints = new ArrayList<>();
                cluster newCluster = new cluster(clusterPoints);
                expandCluster(p, points, corePointsList, newCluster);

                if (newCluster.points.size()>1) { // This line ensures that outlier points don't have their own clusters.
                    allClusters.add(newCluster);
                }
            }
        }

        return allClusters;
    }

    public void expandCluster(point Point, List<point> allPoints, List<corePoint> corePointsList ,cluster Cluster) {
        if (Point.isVisited) {
            return;
        }
        Point.isVisited = true;
        Cluster.points.add(Point);

        // We check if there is a corePoint that has a matching values with this particular point.
        boolean isCorePoint = corePointsList.stream().anyMatch(cp -> cp.values.equals(Point.values));

        if (isCorePoint) {
            //System.out.println("Core point detected: " + Point.values); // Debug statement
    
            // Since we know this point is a core point, find the core point with matching values in corePointsList.
            corePoint correspondingCorePoint = corePointsList.stream()
                .filter(cp -> cp.values.equals(Point.values))
                .findFirst()
                .orElse(null);  // We know this won't return null because of the earlier check.
    
            if (correspondingCorePoint != null) {
                correspondingCorePoint.isVisited = true;
                List<point> neighbours = getNeighbours(correspondingCorePoint, corePointsList);
    
                //System.out.println("Expanding cluster from: " + Point.values + ", found neighbors: " + neighbours.size()); // Debug statement
                
                for (point neighbour : neighbours) {
                    /* Find the corresponding neighbour in allPoints by its values. 
                     * We do this step because getNeighbours returns a new list of points and those points are not marked as visited.
                    */
                    point correspondingNeighbour = allPoints.stream()
                        .filter(p -> p.values.equals(neighbour.values))
                        .findFirst()
                        .orElse(null);

                    if (correspondingNeighbour != null && !correspondingNeighbour.isVisited) {
                        expandCluster(correspondingNeighbour, allPoints, corePointsList, Cluster);
                    }
                }
            }
        }
    }

    public List<point> getNeighbours(corePoint cp, List<corePoint> corePoints) {
        List<point> neighbours = new ArrayList<>();
        for(int i=0 ; i<corePoints.size() ; i++) {
            double distance_Between_Points = 0.0;
            distance_Between_Points = getEuclideanDistance(cp.values, corePoints.get(i).values);
            if(distance_Between_Points <= epsilon) {
                neighbours.add(corePoints.get(i));
            }
        }
        //System.out.println("Neighbors found for " + cp.values + ": " + neighbours.size()); // Debug statement
        return neighbours;
    }

    /*public List<point> getNeighbours(noncorePoint ncp, List<noncorePoint> nonCorePoints) {
        List<point> neighbours = new ArrayList<>();
        for(int i=0 ; i<nonCorePoints.size() ; i++) {
            double distance_Between_Points = 0.0;
            distance_Between_Points = getEuclideanDistance(ncp.values, nonCorePoints.get(i).values);
            if(distance_Between_Points <= epsilon) {
                neighbours.add(nonCorePoints.get(i));
            }
        }
        return neighbours;
    }*/

    public static void main(String[] args) {
        String filePath1 = "data//ClusteringData.csv";
        String filePath2 = "data//parameters.csv";

        csvReader reader = new csvReader();
        List<List<String>> data = reader.readCSV(filePath1);
        List<List<String>> parameters = reader.readCSV(filePath2);

        double epsilon = getEpsilon(parameters);
        int minPts = get_minPts(parameters);

        dbscan DBSCAN = new dbscan(epsilon,minPts);
        
        List<point> points = DBSCAN.createDataPoints(data);

        List<corePoint> corePoints = DBSCAN.createCorePoints(points);
        //List<noncorePoint> nonCorePoints = DBSCAN.createNonCorePoints(points);

        List<cluster> allClusters = DBSCAN.createClusters(points, corePoints);

        System.out.println(allClusters.size());
        
        for (cluster c : allClusters) {
            System.out.println("Cluster size: " + c.points.size());
        }

        //The code below was used for testing specific methods & their outputs.

        //System.out.println(points.size());
        //System.out.println(corePoints.size());
        //System.out.println(nonCorePoints.size());

        /*corePoint a = corePoints.get(25);
        int n = (DBSCAN.getNeighbours(a,corePoints)).size();
        System.out.println(n);*/

        /*noncorePoint b = nonCorePoints.get(75);
        int m = (DBSCAN.getNeighbours(b,nonCorePoints)).size();
        System.out.println(m);*/
    }
}
