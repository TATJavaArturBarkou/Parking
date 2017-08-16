package main.java.com.epam.barkou.parking.controller.command.impl;

import main.java.com.epam.barkou.parking.bean.Slot;
import main.java.com.epam.barkou.parking.bean.SlotReservation;
import main.java.com.epam.barkou.parking.controller.command.Command;
import main.java.com.epam.barkou.parking.controller.util.RequestChecker;
import main.java.com.epam.barkou.parking.service.ISlotService;
import main.java.com.epam.barkou.parking.service.exception.ServiceException;
import main.java.com.epam.barkou.parking.service.factory.ServiceFactory;
import main.java.com.epam.barkou.parking.view.scanner.UserInput;
import org.apache.log4j.Logger;

public class FreeSlot extends Command {
    private final static Logger log = Logger.getLogger(UserInput.class);
    private final static String MESSAGE_SLOT_RELEASED = "Slot has been released successfully";
    private final static String MESSAGE_NO_OCCUPIED_SLOTS = "There aren't any slots occupied by your vehicle";
    private final static String MESSAGE_UNABLE = "Unable to remove Slot";
    private final int vehicleRegNumberIndex = 1;
    private String response = null;

    @Override
    public String execute(String request) {



        String[] requestData = request.split(SPLITTER);

        int paramsQuantity = 1;
        if (RequestChecker.checkParamsQuantity(requestData, paramsQuantity)) {


            String vehicleRegNumber = requestData[vehicleRegNumberIndex];

            SlotReservation slotReservation = new SlotReservation(vehicleRegNumber);


            ServiceFactory factory = ServiceFactory.getInstance();
            ISlotService slotService = factory.getSlotService();

            try {

                boolean is_released = slotService.freeSlot(slotReservation);

                if(is_released == true){
                    response = MESSAGE_SLOT_RELEASED;
                }else{
                    response = MESSAGE_NO_OCCUPIED_SLOTS;
                }


            } catch (ServiceException e) {
                response = MESSAGE_UNABLE;
                log.error(e);

            }

        } else {
            response = MESSAGE_NOT_ENOUGH_PARAMS;
        }
        return response;
    }

}
