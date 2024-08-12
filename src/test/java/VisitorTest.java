import zoosys.model.Visit;
import zoosys.model.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class VisitorTest {

    private Visitor visitor;

    @BeforeEach
    public void setUp() {
        visitor = new Visitor();
    }

    @Test
    public void testAddVisit() {
        visitor.addVisit("1_1_2024", 9, "adult", 5, 4, 4, 4);
        List<Visit> visits = visitor.getVisits();
        assertEquals(1, visits.size());
        Visit visit = visits.get(0);
        assertEquals("1_1_2024", visit.getDate());
        assertEquals(9, visit.getEntryTime());
        assertEquals("adult", visit.getCategory());
        assertEquals(5, visit.getDuration());
        assertEquals(4, visit.getAnimalFeedback());
        assertEquals(4, visit.getCleanlinessFeedback());
        assertEquals(4, visit.getOverallFeedback());
    }

    @Test
    public void testGetTotalVisits() {
        visitor.addVisit("1_1_2024", 9, "adult", 5, 4, 4, 4);
        visitor.addVisit("1_2_2024", 10, "teen", 3, 3, 3, 3);
        assertEquals(2, visitor.getTotalVisits());
    }

    @Test
    public void testGetVisitsCountByDate() {
        visitor.addVisit("1_1_2024", 9, "adult", 5, 4, 4, 4);
        visitor.addVisit("1_1_2024", 10, "teen", 3, 3, 3, 3);
        visitor.addVisit("1_2_2024", 11, "minor", 2, 5, 5, 5);
        assertEquals(2, visitor.getVisitsCountByDate("1_1_2024"));
        assertEquals(1, visitor.getVisitsCountByDate("1_2_2024"));
    }

    @Test
    public void testGetPeakHourByDate() {
        visitor.addVisit("1_1_2024", 9, "adult", 5, 4, 4, 4);
        visitor.addVisit("1_1_2024", 9, "teen", 3, 3, 3, 3);
        visitor.addVisit("1_1_2024", 10, "minor", 2, 5, 5, 5);
        assertEquals(9, visitor.getPeakHourByDate("1_1_2024"));
    }

    @Test
    public void testGetAverageAnimalFeedbackByDate() {
        visitor.addVisit("1_1_2024", 9, "adult", 5, 4, 4, 4);
        visitor.addVisit("1_1_2024", 10, "teen", 3, 3, 3, 3);
        visitor.addVisit("1_1_2024", 11, "minor", 2, 5, 5, 5);
        assertEquals(4.0, visitor.getAverageAnimalFeedbackByDate("1_1_2024"));
    }

    @Test
    public void testGetAverageCleanlinessFeedbackByDate() {
        visitor.addVisit("1_1_2024", 9, "adult", 5, 4, 4, 4);
        visitor.addVisit("1_1_2024", 10, "teen", 3, 3, 3, 3);
        visitor.addVisit("1_1_2024", 11, "minor", 2, 5, 5, 5);
        assertEquals(4.0, visitor.getAverageCleanlinessFeedbackByDate("1_1_2024"));
    }

    @Test
    public void testGetAveragePricingFeedbackByDate() {
        visitor.addVisit("1_1_2024", 9, "adult", 5, 4, 4, 4);
        visitor.addVisit("1_1_2024", 10, "teen", 3, 3, 3, 3);
        visitor.addVisit("1_1_2024", 11, "minor", 2, 5, 5, 5);
        assertEquals(4.0, visitor.getAveragePricingFeedbackByDate("1_1_2024"));
    }
    
    @Test
    public void testGetRevenueByDate() {
        visitor.addVisit("1_1_2024", 9, "adult", 5, 4, 4, 4);
        visitor.addVisit("1_1_2024", 10, "teen", 3, 3, 3, 3);
        visitor.addVisit("1_1_2024", 11, "minor", 2, 5, 5, 5);
        double expectedRevenue = 20.0 + 15.0 + 5.0; // Sum of default prices for adult, teen, and minor
        assertEquals(expectedRevenue, visitor.getRevenueByDate("1_1_2024"));
    }
}
