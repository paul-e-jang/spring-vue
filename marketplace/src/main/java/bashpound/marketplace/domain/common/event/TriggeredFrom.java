package bashpound.marketplace.domain.common.event;

import bashpound.marketplace.utils.IpAddress;

public interface TriggeredFrom {

  /**
   * Get the IP address where the request originated from
   *
   * @return an IP address
   */
  IpAddress getIpAddress();
}
