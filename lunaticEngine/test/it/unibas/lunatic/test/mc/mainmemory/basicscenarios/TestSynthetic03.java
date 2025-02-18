package it.unibas.lunatic.test.mc.mainmemory.basicscenarios;

import it.unibas.lunatic.Scenario;
import it.unibas.lunatic.model.chase.chasemc.operators.ChaseMCScenario;
import it.unibas.lunatic.model.chase.chasemc.DeltaChaseStep;
import it.unibas.lunatic.model.chase.commons.operators.ChaserFactoryMC;
import it.unibas.lunatic.test.References;
import it.unibas.lunatic.test.UtilityTest;
import it.unibas.lunatic.test.checker.CheckExpectedSolutionsTest;
import junit.framework.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSynthetic03 extends CheckExpectedSolutionsTest {

    private static Logger logger = LoggerFactory.getLogger(TestSynthetic03.class);

    public void testScenario() throws Exception {
        Scenario scenario = UtilityTest.loadScenarioFromResources(References.synthetic_03);
        setConfigurationForTest(scenario);
        if (logger.isDebugEnabled()) logger.debug("Scenario\n" + scenario.toString());
//        scenario.getConfiguration().setRemoveDuplicates(true);
        ChaseMCScenario chaser = ChaserFactoryMC.getChaser(scenario);
        DeltaChaseStep result = chaser.doChase(scenario);
//        if (logger.isDebugEnabled()) logger.debug("DeltaDB: " + result.getDeltaDB().printInstances());
        if (logger.isDebugEnabled()) logger.debug(result.toStringWithSort());
        if (logger.isDebugEnabled()) logger.debug("Solutions: " + resultSizer.getPotentialSolutions(result));
        if (logger.isDebugEnabled()) logger.debug("Duplicate solutions: " + resultSizer.getDuplicates(result));
        Assert.assertEquals(6, resultSizer.getSolutions(result));
        Assert.assertEquals(4, resultSizer.getDuplicates(result));
        checkSolutions(result);
        checkExpectedSolutions("expected03", result);
    }

    public void testScenarioNonSymmetric() throws Exception {
        Scenario scenario = UtilityTest.loadScenarioFromResources(References.synthetic_03);
        setConfigurationForTest(scenario);
        if (logger.isDebugEnabled()) logger.debug("Scenario\n" + scenario.toString());
        scenario.getConfiguration().setUseSymmetricOptimization(false);
        scenario.getConfiguration().setDiscardDuplicateTuples(true);
//        scenario.getConfiguration().setRemoveDuplicates(true);
        ChaseMCScenario chaser = ChaserFactoryMC.getChaser(scenario);
        DeltaChaseStep result = chaser.doChase(scenario);
//        if (logger.isDebugEnabled()) logger.debug("DeltaDB: " + result.getDeltaDB().printInstances());
        if (logger.isDebugEnabled()) logger.debug(result.toStringWithSort());
        if (logger.isDebugEnabled()) logger.debug("Solutions: " + resultSizer.getPotentialSolutions(result));
        if (logger.isDebugEnabled()) logger.debug("Duplicate solutions: " + resultSizer.getDuplicates(result));
        Assert.assertEquals(6, resultSizer.getSolutions(result));
        Assert.assertEquals(4, resultSizer.getDuplicates(result));
        checkSolutions(result);
        checkExpectedSolutions("expected03", result);
    }
}
