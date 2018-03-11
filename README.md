# IOTA
IOTA in JAVA

Instruction
1. Execute IotaMain class.
2. Enter the name of device that you want to change status in the console.
3. Enter the name of field or timer that you want to change status in the console.
4. If you enter the timer in name of field, you can change it's timer virtually
5. Enter the state that you want to change in the console.
6. If you enter the timer in name of field, Enter the time that you want to change in the state.

If you want to register some new devices, make new device class using MotionSensor class form.
If you want to program using IOTA syntax, enter the IOTA syntax using Event,
CompPredicate, OneAction, AnyActions class in IotaMain class.

Example Code using IOTA syntax

Event e1 = Event.From(main.devices    .GetDevice("EntranceDoor")    .GetEventElement("Lock"), "UnLocked");  
CompPredicate p1 = CompPredicate.CompEqual(main.devices    .GetDevice("EntranceDoor"), "Lock", "Locked");  
TimerAction a1 = TimerAction.TimerStart(main.devices    .GetDevice("HallwayLight"));  
Rule rule1 = new Rule(e1, p1, a1);

Event e2 = Event.UnConditional(main.devices    .GetDevice("HallwayLight")    .GetEventElement("Timer"));  
CompPredicate p2 = CompPredicate.CompEqual(main.devices    .GetDevice("HallwayLight"), "Timer", "5");  
OneAction a21 = new OneAction(main.devices    .GetDevice("HallwayLight"), "Switch", "Off");  
TimerAction a22 = TimerAction.TimerStop(main.devices    .GetDevice("HallwayLight"));  
AnyActions as2 = new AnyActions();  as2.addAction(a21);  as2.addAction(a22);  
Rule rule2 = new Rule(e2, p2, as2);
RuleSet ruleset = new RuleSet();  ruleset.add(rule1);  ruleset.add(rule2);  
Evaluation eval = new Evaluation(ruleset);

