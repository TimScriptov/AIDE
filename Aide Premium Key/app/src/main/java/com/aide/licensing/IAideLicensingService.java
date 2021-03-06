package com.aide.licensing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAideLicensingService extends IInterface
{

    public static abstract class Stub extends Binder implements IAideLicensingService
	{
        private static final String DESCRIPTOR = "com.aide.licensing.IAideLicensingService";
        static final int TRANSACTION_checkLicense = 1;

        private static class Proxy implements IAideLicensingService
		{
            private IBinder mRemote;

            Proxy(IBinder remote)
			{
                mRemote = remote;
            }

            public IBinder asBinder()
			{
                return mRemote;
            }

            public String getInterfaceDescriptor()
			{
                return Stub.DESCRIPTOR;
            }

            public void checkLicense(IAideLicenseResultListener listener) throws RemoteException
			{
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try
				{
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (listener != null)
					{
                        iBinder = listener.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    mRemote.transact(1, _data, null, 1);
                }
				finally
				{
                    _data.recycle();
                }
            }
        }

        public Stub()
		{
            attachInterface(this, DESCRIPTOR);
        }

        public static IAideLicensingService asInterface(IBinder obj)
		{
            if (obj == null)
			{
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAideLicensingService))
			{
                return new Proxy(obj);
            }
            return (IAideLicensingService) iin;
        }

        public IBinder asBinder()
		{
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException
		{
            switch (code)
			{
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    checkLicense(com.aide.licensing.IAideLicenseResultListener.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void checkLicense(IAideLicenseResultListener iAideLicenseResultListener) throws RemoteException;
}
